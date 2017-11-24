package com.ydy.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.ydy.dto.IndexDto;
import com.ydy.order.model.DivdendDto;
import com.ydy.order.services.IDividendService;
import com.ydy.user.model.User;
import com.ydy.user.services.IUserService;
import com.ydy.utils.Constants;

@Controller
public class UserViewController {
	
	private IService iService = new WxService();

	@Resource(name = IUserService.BEAN_ID)
	private IUserService userService;
	@Resource(name = IDividendService.BEAN_ID)
	private IDividendService dividendService;
	@Autowired
	private HttpSession session;

	@RequestMapping(path = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String wxEntry(HttpServletResponse response) throws IOException, WxErrorException {
		return "redirect:"+iService.oauth2buildAuthorizationUrl("http://superandroidyb.vicp.io/ydy/entry",
				WxConsts.OAUTH2_SCOPE_USER_INFO, "abc");
	}

	@RequestMapping(path = "/entry", method = { RequestMethod.GET, RequestMethod.POST })
	public String entry(String code, String state, Model model) throws WxErrorException {
		WxOAuth2AccessTokenResult oauth2ToGetAccessToken = null;
		if(Constants.accessTokenMap.containsKey(code)){
			oauth2ToGetAccessToken = Constants.accessTokenMap.get(code);
		}else{
			System.out.println("=================code:" + code);
			oauth2ToGetAccessToken = iService.oauth2ToGetAccessToken(code);
			oauth2ToGetAccessToken = iService.oauth2ToGetRefreshAccessToken(oauth2ToGetAccessToken.getRefresh_token());
			Constants.accessTokenMap.put(code, oauth2ToGetAccessToken);
		}
		System.out.println("=================oauth2ToGetAccessToken:" + oauth2ToGetAccessToken);
		User user = userService.findUserByOpenid(oauth2ToGetAccessToken.getOpenid());
		if (user == null) {
			WxUserGet userGet = new WxUserGet();
			userGet.setLang(WxConsts.LANG_CHINA);
			userGet.setOpenid(oauth2ToGetAccessToken.getOpenid());
			System.out.println("=================Access_token:" + oauth2ToGetAccessToken.getAccess_token());
			WxUser wxUser = iService.oauth2ToGetUserInfo(oauth2ToGetAccessToken.getAccess_token(), userGet);
			user = new User();
			BeanUtils.copyProperties(wxUser, user);
			userService.saveUser(user);
			System.out.println("=================wxUser:" + wxUser.toString());
			session.setAttribute(Constants.USER, user);
			return "register";
		} else {
			session.setAttribute(Constants.USER, user);
			if (Constants.UserType.USER_TYPE_BOSS.getCode().equals(user.getUserType())) {
				return "index";
			} else if(Constants.UserType.USER_TYPE_PARTNER.getCode().equals(user.getUserType())){
				if(Constants.UserStatus.USER_STATUS_REGISTER.getCode().equals(user.getStatus())){
					model.addAttribute("tip", "注册成功！");
					return "wait";
				}else{
					user.setNextNum(userService.getNextUserNum(user));
					model.addAttribute("user", user);
					return "user_center";
				}
			} else {
				return "register";
			}
		}
	}

	@RequestMapping(path = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model) {
		IndexDto indexData = userService.getIndexData();
		model.addAttribute("indexDto", indexData);
		return "index";
	}

	@RequestMapping(path = "/partner", method = { RequestMethod.GET, RequestMethod.POST })
	public String partner(Model model) {
		User user = (User) session.getAttribute(Constants.USER);
		model.addAttribute("title", "我推荐的人");
		model.addAttribute("userList", userService.getNextUserList(user));
		return "partner";
	}

	@RequestMapping(path = "/bosspartner", method = { RequestMethod.GET, RequestMethod.POST })
	public String bosspartner(String id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("title", user.getUserName() + "推荐的人");
		model.addAttribute("userList", userService.getNextUserList(user));
		return "partner";
	}

	@RequestMapping(path = "/boss_partner", method = { RequestMethod.GET, RequestMethod.POST })
	public String bossPartner(Model model) {
		model.addAttribute("unUserList", userService.getAllUndeterminedUser());
		model.addAttribute("allUserList", userService.getValidUserList());
		return "boss_partner";
	}
	
	@RequestMapping(path = "/allPartner", method = { RequestMethod.GET, RequestMethod.POST })
	public String allPartner(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		model.addAttribute("title", "本月新增合伙人");
		model.addAttribute("userList", userService.getValidUserList(sdf.format(new Date())));
		return "partner";
	}

	@RequestMapping(path = "/sale_detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String saleDetail(Model model) {
		return "sale_detail";
	}

	@RequestMapping(path = "/confirm", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirm(String id, Model model) {
		model.addAttribute("user", userService.findUserById(id));
		return "confirm";
	}

	@RequestMapping(path = "/register_info", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerInfo(String id, Model model) {
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		return "register_info";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public String register(Model model) {
		return "register";
	}

	@RequestMapping(value = "/user_center", method = { RequestMethod.GET, RequestMethod.POST })
	public String userCenter(Model model, HttpSession session) {
		User user = (User) session.getAttribute(Constants.USER);
		if (user == null) {
			user = userService.getValidUserList().iterator().next();
			session.setAttribute(Constants.USER, user);
		}
		user.setNextNum(userService.getNextUserNum(user));
		model.addAttribute("user", user);
		return "user_center";
	}

	@RequestMapping(value = "/dividend", method = { RequestMethod.GET, RequestMethod.POST })
	public String dividend(String userId, Model model) {
		List<DivdendDto> visBonus = dividendService.getDividendInfo(userId, Constants.VIS_BONUS);
		List<DivdendDto> serverBonus = dividendService.getDividendInfo(userId, Constants.SERVER_BONUS);
		List<DivdendDto> saleBonus = dividendService.getDividendInfo(userId, Constants.SALE_BONUS);
		model.addAttribute("visBonus", visBonus);
		model.addAttribute("serverBonus", serverBonus);
		model.addAttribute("saleBonus", saleBonus);
		model.addAttribute("userId", userId);
		return "dividend";
	}


	@RequestMapping(value = "/wait", method = { RequestMethod.GET, RequestMethod.POST })
	public String wait(Model model) {
		model.addAttribute("tip", "注册成功！");
		return "wait";
	}
}

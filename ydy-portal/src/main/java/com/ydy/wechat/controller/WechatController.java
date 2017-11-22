package com.ydy.wechat.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.exception.WxErrorException;
import com.ydy.utils.Constants;
import com.ydy.wechat.model.WxAddTag;
import com.ydy.wechat.model.WxMenu;
import com.ydy.wechat.model.WxMenu.WxMenuButton;
import com.ydy.wechat.model.WxMenu.WxMenuRule;
import com.ydy.wechat.model.WxTag;

@RestController
public class WechatController {

	private IService iService = new WxService();
	
	@Autowired    
	private HttpSession session; 
	
	/**
	 * 微信处理接口
	 * @author yubin
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/wechatProcess", method = { RequestMethod.GET, RequestMethod.POST })
	public String wechatProcess(HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("UTF-8");  
		
		 //实例化 统一业务API入口
	    IService iService = new WxService();
		
		String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            return echostr;
        }
        
        /** 读取接收到的xml消息 */  
        StringBuffer sb = new StringBuffer();  
        InputStream is = request.getInputStream();  
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
        BufferedReader br = new BufferedReader(isr);  
        String s = "";  
        while ((s = br.readLine()) != null) {  
            sb.append(s);  
        }  
        String xml = sb.toString(); //次即为接收到微信端发送过来的xml数据  
        
        String result = "";  
        /** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */  
        if (StringUtils.isNotBlank(echostr)) {  
            result = echostr;  
        } else {  
            //正常的微信处理流程  
            //result = WechatUtils.parseXmlToEntity(xml);  
        }  
        return result;
	}
	
	@RequestMapping(path = "/createMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public String createMenu() throws WxErrorException, JsonGenerationException, JsonMappingException, IOException{
		String result = "";
		result += doCreateMenu1();
		//result += doCreateMenu2();
		//result += doCreateMenu3();
		return result;
	}
	
	@RequestMapping(path = "/deleteMenu", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteMenu() throws WxErrorException {
		String deleteMenu = iService.deleteMenu();
		return deleteMenu;
	}
	
	public String doCreateMenu1() throws WxErrorException, JsonGenerationException, JsonMappingException, IOException{
		WxMenu menu = new WxMenu();
		List<WxMenuButton> button = new ArrayList<>();
		WxMenuButton btn1 = new WxMenuButton();
		btn1.setKey(Constants.WxBtn.INDEX.getCode());
		btn1.setName(Constants.WxBtn.INDEX.getDesc());
		btn1.setType(WxConsts.BUTTON_VIEW);
		btn1.setUrl("http://www.mydy520.com/");
		button.add(btn1);
		
		WxMenuButton btn2 = new WxMenuButton();
		btn2.setKey(Constants.WxBtn.BOSS_ENTRY.getCode());
		btn2.setName(Constants.WxBtn.BOSS_ENTRY.getDesc());
		btn2.setType(WxConsts.BUTTON_VIEW);
		btn2.setUrl("http://y18571849l.iask.in/ydy/");
		button.add(btn2);
		
		menu.setButton(button);
		return iService.post(WxConsts.URL_CREATE_MENU.replace("ACCESS_TOKEN", iService.getAccessToken()), menu.toJson());
	}
	
	public String doCreateMenu2() throws WxErrorException, JsonGenerationException, JsonMappingException, IOException{
		WxMenu menu = new WxMenu();
		List<WxMenuButton> button = new ArrayList<>();
		WxMenuButton btn1 = new WxMenuButton();
		btn1.setKey(Constants.WxBtn.BOSS.getCode());
		btn1.setName(Constants.WxBtn.BOSS.getDesc());
		List<WxMenuButton> sub_button = new ArrayList<>();
		WxMenuButton e = new WxMenuButton();
		e.setKey(Constants.WxBtn.BOSS_ENTRY.getCode());
		e.setName(Constants.WxBtn.BOSS_ENTRY.getDesc());
		e.setType(WxConsts.BUTTON_CLICK);
		sub_button.add(e);
		btn1.setSub_button(sub_button);
		button.add(btn1);
		menu.setButton(button);
		
		WxMenuRule matchrule = new WxMenuRule();
		matchrule.setTag_id("101");
		menu.setMatchrule(matchrule );
		return iService.post(WxConsts.URL_CREATE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", iService.getAccessToken()), menu.toJson());
	}
	
	public String doCreateMenu3() throws WxErrorException, JsonGenerationException, JsonMappingException, IOException{
		WxMenu menu = new WxMenu();
		List<WxMenuButton> button = new ArrayList<>();
		WxMenuButton btn1 = new WxMenuButton();
		btn1.setKey(Constants.WxBtn.PARTNER.getCode());
		btn1.setName(Constants.WxBtn.PARTNER.getDesc());
		List<WxMenuButton> sub_button = new ArrayList<>();
		WxMenuButton e = new WxMenuButton();
		e.setKey(Constants.WxBtn.PARTNER_CENTER.getCode());
		e.setName(Constants.WxBtn.PARTNER_CENTER.getDesc());
		e.setType(WxConsts.BUTTON_CLICK);
		sub_button.add(e);
		btn1.setSub_button(sub_button);
		button.add(btn1);
		menu.setButton(button);
		
		WxMenuRule matchrule = new WxMenuRule();
		matchrule.setTag_id("102");
		menu.setMatchrule(matchrule );
		return iService.post(WxConsts.URL_CREATE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", iService.getAccessToken()), menu.toJson());
	}
	
	@RequestMapping(path = "/createTag", method = { RequestMethod.GET, RequestMethod.POST })
	public String createTag() throws JsonGenerationException, JsonMappingException, WxErrorException, IOException{
		String str = "";
		str += doCreateTag("老板");
		str += doCreateTag("合伙人");
		return str;
	}
	
	@RequestMapping(path = "/deleteTag", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteTag() throws JsonGenerationException, JsonMappingException, WxErrorException, IOException{
		String str = "";
		return str;
	}
	
	@RequestMapping(path = "/queryTag", method = { RequestMethod.GET, RequestMethod.POST })
	public String queryTag() throws WxErrorException{
		String get = iService.get(Constants.QUERY_TAG_URL.replace("ACCESS_TOKEN", iService.getAccessToken()), null);
		return get;
	}
	
	private String doCreateTag(String tagName) throws JsonGenerationException, JsonMappingException, WxErrorException, IOException{
		WxTag wxTag = new WxTag();
		WxTag.Tag tag = new WxTag.Tag();
		tag.setName(tagName);
		wxTag.setTag(tag);
		
		String post = iService.post(Constants.CREATE_TAG_URL.replace("ACCESS_TOKEN", iService.getAccessToken()), wxTag.toJson());
		return post;
	}
	
	@RequestMapping(path = "/tagging", method = { RequestMethod.GET, RequestMethod.POST })
	public String tagging() throws WxErrorException, JsonProcessingException{
		WxAddTag at = new WxAddTag();
		List<String> openid_list = new ArrayList<>();
		openid_list.add("oY6Evwk5cRxGCaXaqXeIvLhuCbLA");
		at.setOpenid_list(openid_list );
		at.setTagid(102);
		String post = iService.post(Constants.BATCH_TAGGING_URL.replace("ACCESS_TOKEN", iService.getAccessToken()), at.toJson());
		return post;
	}
	
	
	
}

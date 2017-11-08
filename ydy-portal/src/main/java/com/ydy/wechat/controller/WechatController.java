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

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxMenu.WxMenuButton;
import com.soecode.wxtools.exception.WxErrorException;
import com.ydy.utils.Constants;
import com.ydy.wechat.model.UserTag;

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
	public String createMenu() throws WxErrorException{
		boolean condition = false;
		WxMenu menu = new WxMenu();
		List<WxMenuButton> button = new ArrayList<>();
		WxMenuButton btn1 = new WxMenuButton();
		btn1.setKey("index");
		btn1.setName("御鼎园");
		btn1.setType(WxConsts.BUTTON_VIEW);
		btn1.setUrl("http://www.mydy520.com/");
		button.add(btn1);
		
		menu.setButton(button);
		iService.createMenu(menu, condition);
		return "success";
	}
	
	@RequestMapping(path = "/createTag", method = { RequestMethod.GET, RequestMethod.POST })
	public String createTag() throws JsonGenerationException, JsonMappingException, WxErrorException, IOException{
		UserTag tag = new UserTag();
		iService.post(Constants.CREATE_TAG_URL.replace("ACCESS_TOKEN", iService.getAccessToken()), tag.toJson());
		return "success";
	}
	
}

package cn.ct.ks.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import cn.ct.ks.config.SsoConfig;
import cn.ct.ks.constant.ResponseCode;
import cn.ct.ks.dao.UserInfoMapper;
import cn.ct.ks.model.UserInfo;
import cn.ct.ks.utils.RedisUtil;
import cn.ct.ks.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@RequestMapping("sso")
@Controller
public class LoginController {

    @Autowired
    private SsoConfig config;

    @Autowired
    private UserInfoMapper mapper;

    @Autowired
    private RedisUtil util;
    
    private String currentServiceName;

    @RequestMapping("login")
    public String loginPage(@RequestParam String service) {
    	this.currentServiceName = service;
    	return "login";
    }

    @PostMapping("doLogin")
    @ResponseBody
    public Response<String> login(@RequestParam String name, @RequestParam String passwd, HttpServletResponse response) throws IOException {
        UserInfo info = mapper.getUserInfoByName(name);
        if(passwd!=null && info != null &&passwd.equals(info.getPasswd())){
            String id = UUID.randomUUID().toString();
            String head = "ticket";
            String sessionId = head + ":" + id;
            Cookie cookie = new Cookie(config.getCookieKey(), sessionId);
            cookie.setPath("/");
            response.addCookie(cookie);
            util.set(sessionId, info, config.getTimeout());
            String indexUrl = "http://" + config.getHost() + ":" + config.getClientPort() + config.getIndexUrl();
            return new Response<>(indexUrl, ResponseCode.REQUEST_SUCCESS, "登录成功");
        }
        String errorUrl = "http://" + config.getHost() + ":" + config.getClientPort() + config.getLoginErrorUrl();
        return new Response<>(errorUrl, ResponseCode.REQUEST_FAILED, "用户名密码错误");
    }
}

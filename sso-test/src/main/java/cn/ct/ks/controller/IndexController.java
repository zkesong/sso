package cn.ct.ks.controller;

import cn.ct.ks.model.DubboString;
import cn.ct.ks.model.UserInfo;
import cn.ct.ks.service.SsoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class IndexController {

    @Reference(version = "1.0.0")
    private SsoService ssoService;

    @RequestMapping("index")
    public String index(@CookieValue(value = "sessionId",required = false) String sessionId, HttpServletResponse response) throws IOException {
        UserInfo info = ssoService.getUserInfo(sessionId);
        if(info == null){
           response.sendRedirect(ssoService.getLoginUrl(new DubboString("queryService")));
           return "";
        }
        return "欢迎光临主页！！！！！！！！！！！------------" + info.getUsrname();
    }

    @RequestMapping("loginError")
    public String loginError(){
        return "账号密码错误！！！！！！！！！！";
    }
}

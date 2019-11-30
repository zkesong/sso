package cn.ct.ks.service.impl;

import cn.ct.ks.config.SsoConfig;
import cn.ct.ks.model.DubboString;
import cn.ct.ks.model.UserInfo;
import cn.ct.ks.service.SsoService;
import cn.ct.ks.util.WebUtil;
import cn.ct.ks.utils.RedisUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = SsoService.class)
public class SsoServiceImpl implements SsoService {

    @Autowired
    private SsoConfig config;

    @Autowired
    private RedisUtil util;

    @Override
    public String getLoginUrl(DubboString serviceName) {
        return "http://" + config.getHost() + ":" + config.getPort() + "/sso/login?service=" + serviceName.getValue();
    }

    @Override
    public UserInfo getUserInfo(String sessionId) {
        Object obj = util.get(sessionId);
        if(obj != null) {
            return (UserInfo) obj;
        }
        return null;
    }

    @Override
    public void doLogout(String sessionId) {
        util.del(sessionId);
    }
}

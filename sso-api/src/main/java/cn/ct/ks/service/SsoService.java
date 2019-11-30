package cn.ct.ks.service;

import cn.ct.ks.model.DubboString;
import cn.ct.ks.model.UserInfo;

import javax.servlet.http.Cookie;

public interface SsoService {

    String getLoginUrl(DubboString serviceName);

    UserInfo getUserInfo(String sessionId);

    void doLogout(String sessionId);
}

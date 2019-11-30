package cn.ct.ks.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class WebUtil {

    public String getCookieValue(Cookie[] cookies, String key){
        String cookieValue = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    cookieValue = cookie.getValue();
                    System.out.print(cookieValue);
                }
            }
        }
        return cookieValue;
    }

    private WebUtil(){}

    private static class Holder{
        private static final WebUtil INSTANCE = new WebUtil();
    }

    public static final WebUtil getInstance(){
        return Holder.INSTANCE;
    }

}

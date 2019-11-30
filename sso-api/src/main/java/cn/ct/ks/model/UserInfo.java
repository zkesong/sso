package cn.ct.ks.model;

import com.alibaba.fastjson.annotation.JSONType;

import java.io.Serializable;

@JSONType
public class UserInfo implements Serializable {

    private String usrname;

    private String passwd;

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}

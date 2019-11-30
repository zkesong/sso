package cn.ct.ks.model;

import java.io.Serializable;
import java.nio.charset.Charset;

public class DubboString implements Serializable {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private byte[] value;

    public DubboString(){}

    public DubboString(String value){
        this.value = value.getBytes(DEFAULT_CHARSET);
    }

    public String getValue(){
        return new String(value, DEFAULT_CHARSET);
    }

    public byte[] byteValue(){
        return value;
    }
}

package com.ai.zhome.pms.content.utils.security;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

public class BASE64 {

    public static final String encode(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    public static final String decode(String data) {
        return new String(Base64.decodeBase64(data));
    }

    public static final String encoder(String data){
        return new BASE64Encoder().encode(data.getBytes());
    }

    public static final String decoder(String data) throws IOException {
        return new String(new BASE64Decoder().decodeBuffer(data),"utf-8");
    }
}

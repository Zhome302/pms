package com.ai.zhome.pms.content.utils.security;

import org.apache.tomcat.util.codec.binary.Base64;

public class BASE64 {

    public static final String encode(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    public static final String decode(String data) {
        return new String(Base64.decodeBase64(data));
    }
}

package com.ai.zhome.pms.content.utils.security;

import com.ai.zhome.pms.content.utils.exception.CipherException;
import java.security.MessageDigest;

public class JDKDigest {

    private final String algorithm;

    public JDKDigest(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] encrypt(final byte[] src) throws CipherException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(src);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new CipherException("Encrypt Exception , ", e);
        }
    }

}


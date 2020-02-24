package com.ai.zhome.pms.content.utils.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import com.ai.zhome.pms.content.utils.Hex;
import com.ai.zhome.pms.content.utils.exception.CipherException;

public class MD5 {
    private static final String ALGORITHM = "MD5";
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final JDKDigest jdkDigest = new JDKDigest(ALGORITHM);

    private Charset charset;

    public MD5() {
        charset = DEFAULT_CHARSET;
    }

    public MD5(Charset charset) {
        this.charset = charset;
    }

    public byte[] encrypt(final byte[] src) throws CipherException {
        return jdkDigest.encrypt(src);
    }

    public String encrypt(final String src) throws CipherException {
        try {
            byte[] data = encrypt(src.getBytes(charset));
            return Hex.byte2HexString(data);
        } catch (Exception e) {
            throw new CipherException("MD5 Encrypt Exception , ", e);
        }
    }

    /**
     * MD5加密算法，方法二
     *
     * Computes the MD5 fingerprint of a string.
     *
     * @return the MD5 digest of the input <code>String</code>
     */
    public String encode(String s) {
        String cryptograph = "";
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance(ALGORITHM);
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++ ) {
                byte byte0 = md[i];
                str[k++ ] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++ ] = hexDigits[byte0 & 0xf];
            }
            cryptograph = new String(str);// 32位加密

            cryptograph = cryptograph.substring(8, 24);// 16位加密

            return cryptograph;
        } catch (Exception e) {
            return null;
        }

    }
}


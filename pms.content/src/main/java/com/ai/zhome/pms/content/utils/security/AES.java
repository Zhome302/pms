package com.ai.zhome.pms.content.utils.security;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.ai.zhome.pms.content.utils.exception.CipherException;
import org.springframework.util.Assert;

public final class AES {

    private static final int BLOCK_SIZE = 16;

    private static final byte[] INTERNAL_SECRET_KEY = { 0x01, 0x23, 0x45, 0x67, (byte) 0x89,
            (byte) 0xab, (byte) 0xcd, (byte) 0xef, (byte) 0xfe, (byte) 0xdc, (byte) 0xba,
            (byte) 0x98, 0x76, 0x54, 0x32, 0x10 };

    private static final byte[] SHARED_SECRET_KEY = { 1, 8, -49, -31, 77, 90, 10, 121, -14, 109,
            107, 38, 29, 68, 59, 5 };

    private static final String ALGORITHM = "AES";

    /** {@value} */
    public static final String CIPHER_PADDING = "AES/ECB/PKCS5Padding";

    /** {@value} */
    public static final String CIPHER_NOPADDING = "AES/ECB/NoPadding";

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public static final AES INTERNAL_NOPADDING_AES = new AES(INTERNAL_SECRET_KEY, CIPHER_NOPADDING);

    public static final AES INTERNAL_PADDING_AES = new AES(INTERNAL_SECRET_KEY, CIPHER_PADDING);

    public static final AES SHARED_PADDING_AES = new AES(SHARED_SECRET_KEY, CIPHER_PADDING);

    public static final AES SHARED_NOPADDING_AES = new AES(SHARED_SECRET_KEY, CIPHER_NOPADDING);

    public static final AES DEFAULT_AES = INTERNAL_NOPADDING_AES;

    private byte[] secretKey;
    private String cipherAlgorithm;
    private Charset charset;

    public AES(final byte[] secretKey, String cipherAlgorithm) {
        this(secretKey, cipherAlgorithm, DEFAULT_CHARSET);
    }

    public AES(final byte[] secretKey, String cipherAlgorithm, Charset charset) {
        // 暂时不支持更高位32密钥，如果要支持，必须增加jce支持
        // http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
        Assert.isTrue(secretKey.length == 16, "IV length: must be 16 bytes long  ");
        this.secretKey = secretKey.clone();
        this.cipherAlgorithm = cipherAlgorithm;
        this.charset = charset;
    }

    public byte[] decrypt(final byte[] src) throws CipherException {
        try {
            SecretKey deskey = new SecretKeySpec(secretKey, ALGORITHM);
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, deskey);
            return cipher.doFinal(src);
        } catch (Exception e) {
            throw new CipherException("AES Decrypt Exception , ", e);
        }
    }

    public String decrypt(final String outblock) throws CipherException {
        byte[] tmp = decrypt(Base64.getDecoder().decode(outblock));
        return new String(tmp, charset).trim();
    }

    public byte[] encrypt(final byte[] src) throws CipherException {
        try {
            SecretKey deskey = new SecretKeySpec(secretKey, ALGORITHM);
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] data = src;
            if (cipherAlgorithm.equals(CIPHER_NOPADDING)) {
                data = fixBytesBlock(src);
            }
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CipherException("AES Encrypt Exception , ", e);
        }
    }

    public String encrypt(final String inblock) throws CipherException {
        byte[] tmp = encrypt(inblock.getBytes(charset));
        return Base64.getEncoder().encodeToString(tmp);
    }

    /** AES加密nopadding必须补满16*n个字节 */
    private byte[] fixBytesBlock(final byte[] src) {
        byte[] data = src;
        int len = src.length;
        int res = len % BLOCK_SIZE;
        if (res != 0) {
            data = new byte[len - res + BLOCK_SIZE];
            System.arraycopy(src, 0, data, 0, len);
        }
        return data;
    }

}


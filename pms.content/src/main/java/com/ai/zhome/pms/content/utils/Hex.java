package com.ai.zhome.pms.content.utils;

import java.nio.charset.Charset;

/**
 * 十六进制辅助类
 *
 * @author sillywolf
 *
 */
public final class Hex {
    /**
     *
     */
    private static final int UNSIGNED_BYTE_MASK = 0xFF;

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private static final String[] BYTE2HEX_PAD = new String[256];
    private static final String[] BYTE2HEX_NOPAD = new String[256];
    private static final String[] HEXPADDING = new String[16];
    private static final String[] BYTEPADDING = new String[16];

    private static final String[] BYTE2HEX = new String[256];
    private static final char[] BYTE2CHAR = new char[256];

    static {

        int i;
        // Generate the lookup table for hex dump paddings
        for (i = 0; i < HEXPADDING.length; i++) {
            int padding = HEXPADDING.length - i;
            StringBuilder buf = new StringBuilder(padding * 3);
            for (int j = 0; j < padding; j++) {
                buf.append("   ");
            }
            HEXPADDING[i] = buf.toString();
        }

        // Generate the lookup table for byte dump paddings
        for (i = 0; i < BYTEPADDING.length; i++) {
            int padding = BYTEPADDING.length - i;
            StringBuilder buf = new StringBuilder(padding);
            for (int j = 0; j < padding; j++) {
                buf.append(' ');
            }
            BYTEPADDING[i] = buf.toString();
        }

        // Generate the lookup table that converts a byte into a 2-digit
        // hexadecimal integer.
        for (i = 0; i < 10; i++) {
            StringBuilder buf = new StringBuilder(2);
            buf.append('0');
            buf.append(i);
            BYTE2HEX_PAD[i] = buf.toString();
            BYTE2HEX_NOPAD[i] = String.valueOf(i);
        }
        for (; i < 16; i++) {
            StringBuilder buf = new StringBuilder(2);
            char c = (char) ('a' + i - 10);
            buf.append('0');
            buf.append(c);
            BYTE2HEX_PAD[i] = buf.toString();
            BYTE2HEX_NOPAD[i] = String.valueOf(c);
        }
        for (; i < BYTE2HEX_PAD.length; i++) {
            StringBuilder buf = new StringBuilder(2);
            buf.append(Integer.toHexString(i));
            String str = buf.toString();
            BYTE2HEX_PAD[i] = str;
            BYTE2HEX_NOPAD[i] = str;
        }

        for (i = 0; i < BYTE2HEX.length; i++) {
            BYTE2HEX[i] = ' ' + BYTE2HEX_PAD[i & 0xff];
        }

        for (i = 0; i < BYTE2CHAR.length; i++) {
            if (i <= 0x1f || i >= 0x7f) {
                BYTE2CHAR[i] = '.';
            } else {
                BYTE2CHAR[i] = (char) i;
            }
        }
    }

    private static final int HEX = 16;
    private static final int MASK_TAIL_4BITS = 0x0F;
    private static final int MASK_PRE_4BITS = 0xF0;
    private static final String[] HEXCODE = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f" };

    private static final String NEWLINE = "\n";

    /**
     * transfer byte to hex string
     *
     * @param bSrc byte
     * @return hex string
     */
    public static String byte2HexString(byte bSrc) {
        int high = (bSrc & MASK_PRE_4BITS) >> 4;
        int low = bSrc & MASK_TAIL_4BITS;
        return HEXCODE[high] + HEXCODE[low];
    }

    /**
     * transfer byte array to hex string
     *
     * @param bArray byte array
     * @return hex string
     */
    public static String byte2HexString(byte[] bArray) {
        StringBuilder hexBuf = new StringBuilder();
        for (int i = 0; i < bArray.length; i++) {
            hexBuf.append(byte2HexString(bArray[i]));
        }
        return hexBuf.toString();
    }

    /**
     * transfer byte array to hex string ,from offset by len
     *
     * @param buf byte array
     * @param offset 偏移值,从0计数
     * @param len 需要转换的长度
     * @return hex string from byte[offset] to byte[offset+len]
     */
    public static String byte2HexString(byte[] buf, int offset, int len) {
        byte[] tmp = new byte[len];
        System.arraycopy(buf, offset, tmp, 0, len);
        return byte2HexString(tmp);
    }

    /**
     * 将字节数组信息格式化输出
     *
     * @param src
     *            待格式化的字节数组
     * @return 格式化之后的字符串
     */
    public static String formatBytes(byte[] src) {
        int length =src.length;
        int rows = length / 16 + (length % 15 == 0? 0 : 1) + 4;
        StringBuilder dump = new StringBuilder(rows * 80 );

        dump.append("         +-------------------------------------------------+" +
                NEWLINE + "         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |" +
                NEWLINE + "+--------+-------------------------------------------------+----------------+");

        int index;
        for (index = 0; index < length; index++) {
            int indMod16 = index & 15;
            if (indMod16 == 0) {
                dump.append(NEWLINE);
                dump.append(Long.toHexString(index & 0xFFFFFFFFL | 0x100000000L));
                dump.setCharAt(dump.length() - 9, '|');
                dump.append('|');
            }
            dump.append(BYTE2HEX[src[index] & UNSIGNED_BYTE_MASK]);
            if (indMod16 == 15) {
                dump.append(" |");
                for (int j = index - 15; j <= index; j++) {
                    dump.append(BYTE2CHAR[src[j] & UNSIGNED_BYTE_MASK]);
                }
                dump.append('|');
            }
        }

        if ((index & 15) != 0) {
            int remainder = length & 15;
            dump.append(HEXPADDING[remainder]);
            dump.append(" |");
            for (int j = index - remainder; j < index; j++) {
                dump.append(BYTE2CHAR[src[j] & UNSIGNED_BYTE_MASK]);
            }
            dump.append(BYTEPADDING[remainder]);
            dump.append('|');
        }

        dump.append(NEWLINE
                + "+--------+-------------------------------------------------+----------------+");

        return dump.toString();
    }


    /**
     * 将十六进制字符串格式化输出,等同于formatBytes(sHex2Byte(hexStr));
     *
     * @param hexStr
     *            待格式化的十六进制字符串
     * @return 格式化之后的字符串
     */
    public static String formatHexString(String hexStr) {
        return formatBytes(sHex2byte(hexStr));
    }

    /**
     * transfer hex string to byte array
     *
     * @param sHex 十六进制的字符串
     * @return 十六进制字符串等效的byte数组
     */
    public static byte[] sHex2byte(String sHex) {
        int srcLen = sHex.length() / 2;
        byte[] bHex = new byte[srcLen];
        int loc = 0;
        String temp = null;
        for (int i = 0; i < srcLen; i++) {
            temp = String.valueOf(sHex.charAt(i * 2)) + sHex.charAt(i * 2 + 1);
            bHex[loc++] = (byte) Integer.parseInt(temp, HEX);
        }
        return bHex;
    }

    /**
     * transfer hex string to byte array and set to the buf at the offset by the
     * len
     *
     * @param sHex 十六进制的字符串
     * @param buf 存放结果的字节数组
     * @param offset buf的偏移值
     * @param len 保留的长度
     */
    public static void sHex2byte(String sHex, byte[] buf, int offset, int len) {
        byte[] bHex = sHex2byte(sHex);
        System.arraycopy(bHex, 0, buf, offset, len);
    }

    /**
     * 按照默认的UTF-8编码将String转换为hexString
     *
     * @param srcStr
     *            被转换的String
     * @return hex 表示的String
     */
    public static String toHexString(String srcStr) {
        return byte2HexString(srcStr.getBytes(DEFAULT_CHARSET));
    }

    /**
     * 按照指定编码将String转换为hexString
     *
     * @param srcStr
     *            被转换的String
     * @param charset
     *            指定的字符集
     * @return hex 表示的String
     */
    public static String toHexString(String srcStr, Charset charset) {
        return byte2HexString(srcStr.getBytes(charset));
    }

    private Hex() {
    }

}

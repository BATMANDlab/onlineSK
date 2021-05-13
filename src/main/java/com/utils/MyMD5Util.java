package com.utils;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * @author pibigstar
 *
 */
public class MyMD5Util {


    /** 字符串，计算MD5值 */
    public static String MD5(String data)
    {
        return MD5(data.getBytes());
    }

    /** 字节数组，计算MD5值 */
    public static String MD5(byte[] data)
    {
        try
        {
            // 获取data的MD5摘要
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // mdInst.update(content.getBytes());
            digest.update(data);
            byte[] array = digest.digest();

            // 转换为十六进制的字符串形式
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < array.length; i++)
            {
                String shaHex = Integer.toHexString(array[i] & 0xFF);
                if (shaHex.length() < 2)
                {
                    buf.append(0);
                }
                buf.append(shaHex);
            }
            return buf.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }


}


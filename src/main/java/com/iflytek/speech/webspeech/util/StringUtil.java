package com.iflytek.speech.webspeech.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * title:
 * description:
 * project:
 * company:
 * copyright:
 *
 * @author xuys
 * @version 1.0.0
 * @data 2017年8月4日 上午10:09:59
 */
public class StringUtil {
    private final static Log LOGGER = LogFactory.getLog(StringUtil.class);

    /**
     * MD5加密
     *
     * @param str
     * @return
     */
    public static String makeMD5(String str) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            String pwd = new BigInteger(1, md.digest()).toString(16);
            return pwd;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return str;
    }

    /**
     * 获取hash值
     *
     * @param map
     * @return
     */
    public final static String mapToString(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        return map.toString().replaceAll("=", ":").replaceAll(",", ";")
                .replaceAll("\\{", "").replaceAll("\\}", "");
    }

    /**
     * 生成UUID主键
     *
     * @return
     */
    public static String genUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 截取行政区编码
     *
     * @param areaCode
     * @param level
     * @return
     */
    public static String trimAreaCode(String areaCode, Integer level) {

        String startAreaCode = "";
        if (areaCode != null) {
            if (level == 1) {
                startAreaCode = areaCode.substring(0, 2);
            } else if (level == 2) {
                startAreaCode = areaCode.substring(0, 4);
            }
        }
        return startAreaCode;
    }

    /**
     * 判断字符是否为空或者空串
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        }

        return true;
    }

    public static String setNoNull(Object o) {
        if (o == null)
            return "";
        return o.toString();
    }

    /**
     * 生成随机数字和字母,
     *
     * @param length
     * @return
     */
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}

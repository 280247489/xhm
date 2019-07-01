package com.memory.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @Auther: cui.Memory
 * @Date: 2018/11/7 0007 16:48
 * @Description:
 */
public class Utils {
    /**
     * 生成32位UUID字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };
    public static String getShortUUID() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat sf_yMd = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat idTimer = new SimpleDateFormat("yyyyMMddHHmmss");


    /**
     * 判断是否为基本类型：包括String/Integer/Double/Boolean
     * @param clazz clazz
     * @return  true：是;     false：不是
     */
    public static boolean isPrimite(Class<?> clazz){
        if (clazz.isPrimitive() || clazz == String.class || clazz == Integer.class || clazz == Double.class || clazz == Boolean.class){
            return true;
        }else {
            return false;
        }
    }


    public static Boolean isNotNull(String str){
        return  str!=null && str.length()>0;
    }

    public static Boolean isNotNull(Integer str){
        return  str!=null ;
    }

    public static Boolean isNotNull(Boolean str){
        return  str!=null ;
    }

    public static Boolean isNotNull(MultipartFile file){
        return file!=null && !file.isEmpty();
    }

    public static Boolean isNotNull(Object file){
        return file!=null;
    }



}

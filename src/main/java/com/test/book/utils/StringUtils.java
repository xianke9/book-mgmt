package com.test.book.utils;

import java.nio.charset.StandardCharsets;

public class StringUtils {


    public static String unicodeEncode(String source) {
        byte[] unicodeBytes = source.getBytes(StandardCharsets.UTF_16);

        // 将 Unicode 编码的字节数组转换为字符串
        String decodedString = new String(unicodeBytes, java.nio.charset.StandardCharsets.UTF_16);
        return decodedString;

    }


    public static String inputBlank(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String inputNChar(int n,String c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static <T> String append(T ...strs){
        if(strs!=null && strs.length > 0){
            StringBuilder sb = new StringBuilder();
            for(T s:strs){
                sb.append(s);
            }
            return sb.toString();
        }
        return "";
    }


    public static String inputBalank(int n, String str,int maxlen) {
       String tmp =  String.format("%-"+maxlen+"s", str);
       return StringUtils.append(StringUtils.inputBlank(n), tmp, StringUtils.inputBlank(n));
    }

    public static String inputBalankWithSplit(int n, String str, int maxlen) {
        String tmp =  String.format("%-"+maxlen+"s", str);
        return StringUtils.append(StringUtils.inputBlank(n), tmp, StringUtils.inputBlank(n),"|");
    }

    public static void main(String[] args) {
        System.out.println(inputBalank(2,"test",20).length());
        System.out.println(inputBalank(2,"test1",20).length());
    }

}

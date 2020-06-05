package com.myliabilities.utils;


public class MyStringUtils {

    public static boolean isEmpty(String str) {
        if(str == null || "".equals(str)) {
            return true;
        }
        return false;
    }
}

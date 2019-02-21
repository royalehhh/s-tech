package com.stk.website.util;

public class PageUtil {

    public static long getTotalPage(long total, long row){
        return (total+row-1)/row;
    }
}

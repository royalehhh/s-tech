package com.stk.website.comm;

public class ErrorConstant {

    //不存在
    public static Integer USER_NOT_EXIST_CODE= 404000;
    public static String USER_NOT_EXIST_MSG= "user not exist";
    public static Integer DATABASE_NO_DATA_CODE= 404001;
    public static String DATABASE_NO_DATA_MSG= "database no data";




    //请求错误
    public static Integer PARAM_INCOMPLETE_CODE= 500000;
    public static String PARAM_INCOMPLETE_MSG= "param incomplete";

    public static Integer FILE_TOO_LARGE_CODE= 500001;
    public static String FILE_TOO_LARGE_MSG= "the file is too large!!!";

    //无访问权限
    public static Integer NO_PERMISSION_CODE= 501000;
    public static String NO_PERMISSION_MSG= "permission denied";

}

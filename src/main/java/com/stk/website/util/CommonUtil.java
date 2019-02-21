package com.stk.website.util;


import java.util.Collection;
import java.util.Map;

/**
 * @author Royle.Huang
 * @date 2019/2/21 19:21
 * @description: 辅助类
 */
public abstract class CommonUtil {


    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if ("".equals(pObj)) {
            return true;
        }
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null) {
            return false;
        }
        if ("".equals(pObj)) {
            return false;
        }
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean isNotEmptys(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return false;
            }
            if ("".equals(obj)) {
                return false;
            }
            if (obj instanceof String) {
                if (((String) obj).length() == 0) {
                    return false;
                }
            } else if (obj instanceof Collection) {
                if (((Collection) obj).size() == 0) {
                    return false;
                }
            } else if (obj instanceof Map) {
                if (((Map) obj).size() == 0) {
                    return false;
                }
            }
        }
        return true;
    }


}

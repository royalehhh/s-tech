package com.stk.website.comm;


import com.stk.website.dto.inner.PageRequest;
import com.stk.website.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author royle.huang
 * @Date 2019/2/1 14:32
 * @Description 全局变量和方法
 **/
public class Global {

    public static Map<String, String> tokenMap = new HashMap<>();


    public static QueryPage getQueryPage(PageRequest pager) {
        QueryPage queryPage = new QueryPage();
        if (CommonUtil.isNotEmpty(pager.getPage())) {
            queryPage.setTargetPage(pager.getPage());
        }
        if (CommonUtil.isNotEmpty(pager.getRow())) {
            queryPage.setPageSize(pager.getRow());
        }
        if (CommonUtil.isNotEmpty(pager.getSort())) {
            queryPage.setSort(pager.getSort());
        }
        if (CommonUtil.isNotEmpty(pager.getOrder())) {
            queryPage.setOrder(pager.getOrder());
        }
        return queryPage;
    }
}

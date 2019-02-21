package com.stk.website.comm;

import java.io.Serializable;

/**
 * @Author royle.huang
 * @Date 2019/2/12 10:26
 * @Description 分页
 **/
public class Page implements Serializable {

    private static final long serialVersionUID = 546801591726778454L;
    private Integer page;//页码
    private Integer rows;//每页显示条数
    private String sort;//排序字段
    private String order;//排序方式

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

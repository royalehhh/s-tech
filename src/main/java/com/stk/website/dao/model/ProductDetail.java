package com.stk.website.dao.model;

import java.io.Serializable;

/**
 * product_detail
 * @author 
 */
public class ProductDetail implements Serializable {
    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 性能
     */
    private String functionName;

    /**
     * 排序
     */
    private Integer index;

    /**
     * 操作界面
     */
    private String functionDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }
}
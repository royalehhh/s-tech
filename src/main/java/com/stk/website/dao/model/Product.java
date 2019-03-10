package com.stk.website.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * product
 * @author 
 */

@Data
public class Product implements Serializable {
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String img;

    /**
     * 型号
     */
    private String model;

    /**
     * 说明
     */
    private String desc;

    /**
     * 是否首页展示 0 不展示 1.展示
     */
    private Integer showHome;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    List<ProductDetail> details;

    private static final long serialVersionUID = 1L;

}
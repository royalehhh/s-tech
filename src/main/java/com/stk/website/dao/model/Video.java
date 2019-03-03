package com.stk.website.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * video
 * @author 
 */
public class Video implements Serializable {
    private Integer id;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频地址
     */
    private String url;

    /**
     * 上传时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
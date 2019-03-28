package com.stk.website.dao.model;

import java.io.Serializable;

/**
 * temp_file
 * @author 
 */
public class TempFile implements Serializable {
    private Integer id;

    private String filePath;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
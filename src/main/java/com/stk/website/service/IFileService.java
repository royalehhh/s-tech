package com.stk.website.service;

import com.stk.website.dto.FileResponse;

/**
 * @Author royle.huang
 * @Date 14:50 2019/3/28
 * @Description 文件service
 **/
public interface IFileService {
    /**
     * @Author royle.huang
     * @Date 14:58 2019/3/28
     * @Description 添加文件
     **/
    FileResponse addFile(String filePath);
}

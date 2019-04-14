package com.stk.website.service.impl;

import com.stk.website.dao.mapper.TempFileMapper;
import com.stk.website.dao.model.TempFile;
import com.stk.website.dto.FileResponse;
import com.stk.website.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author royle.huang
 * @Date 14:51 2019/3/28
 * @Description 文件service
 **/
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private TempFileMapper tempFileMapper;

    @Override
    public FileResponse addFile(String filePath) {
        FileResponse response = new FileResponse();
        TempFile tempFile = new TempFile();
        tempFile.setFilePath(filePath);
        tempFileMapper.insert(tempFile);
        response.setTempFile(tempFile);
        return response;
    }
}

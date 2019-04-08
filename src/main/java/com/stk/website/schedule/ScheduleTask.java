package com.stk.website.schedule;

import com.stk.website.dao.mapper.TempFileMapper;
import com.stk.website.dao.model.TempFile;
import com.stk.website.dao.model.TempFileExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.List;

@Component
@Configuration
@EnableScheduling
@Slf4j
public class ScheduleTask {

    @Autowired
    TempFileMapper tempFileMapper;

    public void deleteTempFile(){
        log.info("run deleteTempFile: "+new Date());
        TempFileExample example = new TempFileExample();
        TempFileExample.Criteria criteria = example.createCriteria();
        List<TempFile> list = tempFileMapper.selectByExample(example);
        if (list != null && !list.isEmpty()){
            for (TempFile tempFile : list) {
                File file = new File(tempFile.getFilePath());
                log.info("start delete file: " +tempFile.getFilePath());
                file.delete();
                log.info("end delete file: " +tempFile.getFilePath());
                tempFileMapper.deleteByPrimaryKey(tempFile.getId());
            }
        }
    }
}

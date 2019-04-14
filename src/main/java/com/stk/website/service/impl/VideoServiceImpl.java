package com.stk.website.service.impl;

import com.stk.website.comm.ErrorConstant;
import com.stk.website.comm.Global;
import com.stk.website.dao.mapper.TempFileMapper;
import com.stk.website.dao.mapper.VideoMapper;
import com.stk.website.dao.model.TempFile;
import com.stk.website.dao.model.Video;
import com.stk.website.dao.model.VideoExample;
import com.stk.website.dto.VideoResponse;
import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;
import com.stk.website.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author Royle.Huang
 * @date 2019/3/2 18:03
 * @description: 视频service
 */
@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    VideoMapper videoMapper;
    @Autowired
    TempFileMapper tempFileMapper;

    @Value("${path.upload.folder}")
    private String uploadFolder;

    @Override
    public PageResponse<Video> queryVideoListByPage(PageRequest request) {
        PageResponse<Video> response = new PageResponse<>();
        VideoExample example = new VideoExample();
        example.setOffset(request.getLimitStart());
        example.setLimit(request.getRow());
        example.setOrderByClause("create_time");
        List<Video> list = videoMapper.selectByExample(example);
        long total = videoMapper.countByExample(example);
        response.setPageList(list);
        response.setTotalCount(total);
        response.setTotalPage(Global.getTotalPage(total, request.getRow()));
        return response;
    }

    @Override
    public VideoResponse queryVideoDetail(Integer id) {
        VideoResponse response = new VideoResponse();
        Video video = videoMapper.selectByPrimaryKey(id);
        response.setVideo(video);
        return response;
    }

    @Override
    public BaseResponse addVideo(Video video, Integer fileId) {
        BaseResponse response = new BaseResponse();
        video.setCreateTime(new Date());
        videoMapper.insert(video);
        tempFileMapper.deleteByPrimaryKey(fileId);
        return response;
    }

    @Override
    public BaseResponse editVideo(Video video, Integer fileId) {
        BaseResponse response = new BaseResponse();
        Video bean = videoMapper.selectByPrimaryKey(video.getId());
        if (bean==null){
            response.setCode(ErrorConstant.DATABASE_NO_DATA_CODE);
            response.setMsg(ErrorConstant.DATABASE_NO_DATA_MSG);
            return response;
        }
        String oldFilePath = bean.getUrl();
        if (fileId != null){
            TempFile tempFile = tempFileMapper.selectByPrimaryKey(fileId);
            if (tempFile!=null){
                File file = new File(uploadFolder+oldFilePath);
                file.delete();
            }
        }
        video.setCreateTime(bean.getCreateTime());
        videoMapper.updateByPrimaryKey(video);
        return response;
    }

    @Override
    public BaseResponse deleteVideo(Integer id) {
        BaseResponse response = new BaseResponse();
        Video bean = videoMapper.selectByPrimaryKey(id);
        if (bean!=null){
            String oldFilePath = bean.getUrl();
            File file = new File(uploadFolder+oldFilePath);
            file.delete();
        }
        videoMapper.deleteByPrimaryKey(id);
        return response;

    }
}

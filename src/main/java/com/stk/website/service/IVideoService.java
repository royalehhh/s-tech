package com.stk.website.service;

import com.stk.website.dao.model.Video;
import com.stk.website.dto.VideoResponse;
import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;

public interface IVideoService {
    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:32
     * @description: 查询视频列表
     */
    PageResponse<Video> queryVideoListByPage(PageRequest request);

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:41
     * @description: 查询视频详情
     */
    VideoResponse queryVideoDetail(Integer id);

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:46
     * @description: 添加视频
     */
    BaseResponse addVideo(Video video);

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:49
     * @description: 编辑视频信息
     */
    BaseResponse editVideo(Video video);

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:53
     * @description: 删除视频
     */
    BaseResponse deleteVideo(Integer id);
}

package com.stk.website.dto;

import com.stk.website.dao.model.Video;
import com.stk.website.dto.inner.BaseResponse;
import lombok.Data;

@Data
public class VideoResponse extends BaseResponse {

    private Video video;
}

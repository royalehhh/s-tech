package com.stk.website.dto;

import com.stk.website.dao.model.News;
import com.stk.website.dto.inner.BaseResponse;
import lombok.Data;

@Data
public class NewsResponse extends BaseResponse {
    private News news;
}

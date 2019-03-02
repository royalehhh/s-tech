package com.stk.website.service;

import com.stk.website.dao.model.News;
import com.stk.website.dto.NewsResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;

public interface INewsService {
    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:19
     * @description: 查询新闻列表
     */
    PageResponse<News> queryNewsListByPage(PageRequest request);

    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:28
     * @description: 查询新闻详情
     */
    NewsResponse queryNewsDetail(Integer id);
}

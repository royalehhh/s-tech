package com.stk.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.stk.website.comm.ErrorConstant;
import com.stk.website.dao.model.News;
import com.stk.website.dao.model.Product;
import com.stk.website.dto.NewsRequest;
import com.stk.website.dto.NewsResponse;
import com.stk.website.dto.ProductRequest;
import com.stk.website.dto.ProductResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;
import com.stk.website.exception.ServiceException;
import com.stk.website.service.INewsService;
import com.stk.website.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WebSiteController {

    @Autowired
    IProductService productService;
    @Autowired
    INewsService newsService;


    /**
     * @author Royle.Huang
     * @date 2019/3/2 11:48
     * @description: 产品列表
     */
    @PostMapping("/product/list")
    public PageResponse<Product> queryProductListByPage(@RequestBody String json) {
        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        PageResponse<Product> response = productService.queryProductListByPage(request);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 11:49
     * @description: 产品详情
     */
    @PostMapping("/product/detail")
    public ProductResponse queryProductDetail(@RequestBody String json){
        ProductRequest request = JSONObject.parseObject(json, ProductRequest.class);
        Integer productId = request.getId();
        if (null == productId){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        ProductResponse response = productService.queryProductDetail(productId);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 12:22
     * @description: 新闻列表
     */
    @PostMapping("/news/list")
    public PageResponse<News> queryNewsListByPage(@RequestBody String json){
        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        PageResponse<News> response = newsService.queryNewsListByPage(request);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:23
     * @description: 新闻详情
     */
    @PostMapping("/news/detail")
    public NewsResponse queryNewsDetail(@RequestBody String json){
        NewsRequest request = JSONObject.parseObject(json, NewsRequest.class);
        if (request.getId()==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        NewsResponse response = newsService.queryNewsDetail(request.getId());
        return response;
    }


}

package com.stk.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.stk.website.comm.ErrorConstant;
import com.stk.website.dao.model.News;
import com.stk.website.dao.model.Product;
import com.stk.website.dao.model.Video;
import com.stk.website.dto.*;
import com.stk.website.dto.inner.BaseRequest;
import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;
import com.stk.website.exception.ServiceException;
import com.stk.website.service.INewsService;
import com.stk.website.service.IProductService;
import com.stk.website.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class WebSiteController {

    @Autowired
    IProductService productService;
    @Autowired
    INewsService newsService;
    @Autowired
    IVideoService videoService;


    /**
     * @author Royle.Huang
     * @date 2019/3/3 14:49
     * @description: 首页产品
     */
    @PostMapping("/product/home")
    public HomeProductResponse queryProductListHome() {
        HomeProductResponse response = productService.queryProductListHome();
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 11:48
     * @description: 产品列表
     */
    @PostMapping("/product/list")
    public PageResponse<Product> queryProductListByPage(PageRequest request) {
//        log.info("invoke website queryProductListByPage request: " + json);
//        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        PageResponse<Product> response = productService.queryProductListByPage(request);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 11:49
     * @description: 产品详情
     */
    @PostMapping("/product/detail")
    public ProductResponse queryProductDetail(ProductRequest request){
//        log.info("invoke website queryProductDetail request: " + json);
//        ProductRequest request = JSONObject.parseObject(json, ProductRequest.class);
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
    public PageResponse<News> queryNewsListByPage(PageRequest request){
//        log.info("invoke website queryNewsListByPage request: " + json);
//        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        PageResponse<News> response = newsService.queryNewsListByPage(request);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:23
     * @description: 新闻详情
     */
    @PostMapping("/news/detail")
    public NewsResponse queryNewsDetail(NewsRequest request){
//        log.info("invoke website queryNewsDetail request: " + json);
//        NewsRequest request = JSONObject.parseObject(json, NewsRequest.class);
        if (request.getId()==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        NewsResponse response = newsService.queryNewsDetail(request.getId());
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/10 11:53
     * @description: 首页新闻
     */
    @PostMapping("/news/home")
    public PageResponse<News> queryNewsListHome(){
//        log.info("invoke website queryNewsListByPage request: " + json);
//        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        PageRequest request = new PageRequest();
        request.setRow(4);
        request.setPage(1);
        PageResponse<News> response = newsService.queryNewsListByPage(request);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 18:00
     * @description: 视频列表
     */
    @PostMapping("/video/list")
    public PageResponse<Video> queryVideoListByPage(PageRequest request){
//        log.info("invoke website queryVideoListByPage request: " + json);
//        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        PageResponse<Video> response = videoService.queryVideoListByPage(request);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:37
     * @description: 视频详情
     */
    @PostMapping("/video/detail")
    public VideoResponse queryVideoDetail(VideoRequest request){
//        log.info("invoke website queryVideoDetail request: " + json);
//        VideoRequest request = JSONObject.parseObject(json, VideoRequest.class);
        Integer id = request.getId();
        if (id == null) {
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        VideoResponse response = videoService.queryVideoDetail(id);
        return response;
    }



}

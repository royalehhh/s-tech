package com.stk.website.controller;


import com.alibaba.fastjson.JSONObject;
import com.stk.website.comm.ErrorConstant;
import com.stk.website.dao.model.News;
import com.stk.website.dao.model.Product;
import com.stk.website.dao.model.ProductDetail;
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
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/manage")
@Slf4j
public class BackStageController {

    @Autowired
    IProductService productService;
    @Autowired
    INewsService newsService;
    @Autowired
    IVideoService videoService;

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:04
     * @description: 删除产品详情
     */
    @DeleteMapping("/product/detail")
    public BaseResponse deleteProductDetail(ProductRequest request){
//        log.info("invoke deleteProductDetail: " + json);
//        ProductRequest request = JSONObject.parseObject(json, ProductRequest.class);
        if (request.getId() == null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = productService.deleteProductDetail(request.getId());
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:01
     * @description: 编辑产品详情
     */
    @PostMapping("/product/detail/edit")
    public BaseResponse editProductDetail(ProductDetail productDetail){
//        log.info("invoke editProductDetail: " + json);
//        ProductDetail productDetail = JSONObject.parseObject(json, ProductDetail.class);
        if (productDetail.getId() == null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = productService.editProductDetail(productDetail);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:58
     * @description: 添加产品详情
     */
    @PostMapping("/product/detail/add")
    public BaseResponse addProductDetail(ProductDetail productDetail){
//        log.info("invoke addProductDetail: " + json);
//        ProductDetail productDetail = JSONObject.parseObject(json, ProductDetail.class);
        if (productDetail.getProductId()==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = productService.addProductDetail(productDetail);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:08
     * @description: 删除产品
     */
    @DeleteMapping("/product")
    public BaseResponse deleteProduct(ProductRequest request){
//        log.info("invoke deleteProduct: " + json);
//        ProductRequest request = JSONObject.parseObject(json, ProductRequest.class);
        if (request.getId() == null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = productService.deleteProduct(request.getId());
        return response;
    }
    
    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:51 
     * @description: 编辑产品
     */
    @PostMapping("/product/edit")
    public BaseResponse editProduct(Product product){
//        log.info("invoke editProduct: " + json);
//        Product product = JSONObject.parseObject(json, Product.class);
        if (product.getId() == null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = productService.editProduct(product);
        return response;
    }
    
    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:37
     * @description: 添加产品
     */
    @PostMapping("/product/add")
    public BaseResponse addProduct(Product product){
//        log.info("invoke addProduct: " + json);
//        Product product = JSONObject.parseObject(json, Product.class);
        BaseResponse response = productService.addProduct(product);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 11:48
     * @description: 产品列表
     */
    @PostMapping("/product/list")
    public PageResponse<Product> queryProductListByPage(PageRequest request) {
//        log.info("invoke queryProductListByPage: " + json);
//        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        request.setRow(request.getLimit());
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
//        log.info("invoke queryProductDetail: " + json);
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
//        log.info("invoke queryNewsListByPage: " + json);
//        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        request.setRow(request.getLimit());
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
//        log.info("invoke queryNewsDetail: " + json);
//        NewsRequest request = JSONObject.parseObject(json, NewsRequest.class);
        if (request.getId()==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        NewsResponse response = newsService.queryNewsDetail(request.getId());
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:12
     * @description: 添加新闻
     */
    @PostMapping("/news/add")
    public BaseResponse addNews(News news){
//        log.info("invoke addNews: " + json);
//        News news = JSONObject.parseObject(json, News.class);
        news.setCreateTime(new Date());
        news.setUpdateTime(new Date());
        BaseResponse response = newsService.addNews(news);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:14
     * @description: 编辑新闻
     */
    @PostMapping("/news/edit")
    public BaseResponse editNews(News news){
//        log.info("invoke editNews: " + json);
//        News news = JSONObject.parseObject(json, News.class);
        if (news.getId()==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        news.setUpdateTime(new Date());
        BaseResponse response = newsService.editNews(news);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:18
     * @description: 删除新闻
     */
    @DeleteMapping("/news")
    public BaseResponse deleteNews(NewsRequest request){
//        log.info("invoke deleteNews: " + json);
//        NewsRequest request = JSONObject.parseObject(json, NewsRequest.class);
        if (request.getId() == null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = newsService.deleteNews(request.getId());
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 18:00
     * @description: 视频列表
     */
    @PostMapping("/video/list")
    public PageResponse<Video> queryVideoListByPage(PageRequest request){
//        log.info("invoke queryVideoListByPage: " + json);
//        PageRequest request = JSONObject.parseObject(json, PageRequest.class);
        request.setRow(request.getLimit());
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
//        log.info("invoke queryVideoDetail: " + json);
//        VideoRequest request = JSONObject.parseObject(json, VideoRequest.class);
        Integer id = request.getId();
        if (id == null) {
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        VideoResponse response = videoService.queryVideoDetail(id);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:43
     * @description: 添加视频
     */
    @PostMapping("/video/add")
    public BaseResponse addVideo(Video video){
//        log.info("invoke addVideo: " + json);
//        Video video = JSONObject.parseObject(json, Video.class);
        BaseResponse response = videoService.addVideo(video);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:47
     * @description: 编辑视频
     */
    @PostMapping("/video/edit")
    public BaseResponse editVideo(Video video){
//        log.info("invoke editVideo: " + json);
//        Video video = JSONObject.parseObject(json, Video.class);
        if (video.getId() == null) {
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = videoService.editVideo(video);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:51
     * @description: 删除视频
     */
    @DeleteMapping("/video")
    public BaseResponse deleteVideo(VideoRequest request){
//        log.info("invoke deleteVideo: " + json);
//        VideoRequest request = JSONObject.parseObject(json, VideoRequest.class);
        if (request.getId() == null) {
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = videoService.deleteVideo(request.getId());
        return response;
    }
}

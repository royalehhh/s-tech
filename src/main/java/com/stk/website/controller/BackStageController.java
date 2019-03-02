package com.stk.website.controller;


import com.alibaba.fastjson.JSONObject;
import com.stk.website.comm.ErrorConstant;
import com.stk.website.dao.model.News;
import com.stk.website.dao.model.Product;
import com.stk.website.dao.model.ProductDetail;
import com.stk.website.dto.NewsRequest;
import com.stk.website.dto.NewsResponse;
import com.stk.website.dto.ProductRequest;
import com.stk.website.dto.ProductResponse;
import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;
import com.stk.website.exception.ServiceException;
import com.stk.website.service.INewsService;
import com.stk.website.service.IProductService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class BackStageController {

    @Autowired
    IProductService productService;
    @Autowired
    INewsService newsService;

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:04
     * @description: 删除产品详情
     */
    @DeleteMapping("/product/detail")
    public BaseResponse deleteProductDetail(@RequestBody String json){
        ProductRequest request = JSONObject.parseObject(json, ProductRequest.class);
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
    public BaseResponse editProductDetail(@RequestBody String json){
        ProductDetail productDetail = JSONObject.parseObject(json, ProductDetail.class);
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
    public BaseResponse addProductDetail(@RequestBody String json){
        ProductDetail productDetail = JSONObject.parseObject(json, ProductDetail.class);
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
    public BaseResponse deleteProduct(@RequestBody String json){
        ProductRequest request = JSONObject.parseObject(json, ProductRequest.class);
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
    public BaseResponse editProduct(@RequestBody String json){
        Product product = JSONObject.parseObject(json, Product.class);
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
    public BaseResponse addProduct(@RequestBody String json){
        Product product = JSONObject.parseObject(json, Product.class);
        BaseResponse response = productService.addProduct(product);
        return response;
    }

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

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:12
     * @description: 添加新闻
     */
    @PostMapping("/news/add")
    public BaseResponse addNews(@RequestBody String json){
        News news = JSONObject.parseObject(json, News.class);
        BaseResponse response = newsService.addNews(news);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:14
     * @description: 编辑新闻
     */
    @PostMapping("/news/edit")
    public BaseResponse editNews(@RequestBody String json){
        News news = JSONObject.parseObject(json, News.class);
        if (news.getId()==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = newsService.editNews(news);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:18
     * @description: 删除新闻
     */
    @DeleteMapping("/news")
    public BaseResponse deleteNews(@RequestBody String json){
        NewsRequest request = JSONObject.parseObject(json, NewsRequest.class);
        if (request.getId() == null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = newsService.deleteNews(request.getId());
        return response;
    }
}

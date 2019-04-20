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
import com.stk.website.service.IFileService;
import com.stk.website.service.INewsService;
import com.stk.website.service.IProductService;
import com.stk.website.service.IVideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    IFileService fileService;

    @Value("${path.upload.folder}")
    private String uploadFolder;
    @Value("${path.prefix.folder}")
    private String filePrefix;
    @Value("${path.prefix.url}")
    private String urlPrefix;

    /**
     * @Author royle.huang
     * @Date 14:24 2019/3/28
     * @Description 文件上传
     **/
    @PostMapping("/upload")
    public BaseResponse uploadFiles(@RequestParam("file") MultipartFile file){
        File folder = new File(uploadFolder);
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (file.isEmpty()) {
            return new BaseResponse(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }

        if (file.getSize()>1024*1024*1024){
            return new BaseResponse(ErrorConstant.FILE_TOO_LARGE_CODE, ErrorConstant.FILE_TOO_LARGE_MSG);
        }
        String oldName = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+oldName.substring(oldName.lastIndexOf("."));
        String filePath = uploadFolder + filePrefix +fileName;
        File newFile = new File(filePath);
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFile));
            out.write(bytes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse(500002, "文件上传失败");
        }
        //插入数据库
        FileResponse response = fileService.addFile(filePrefix+fileName);
        return response;
    }

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
    public BaseResponse editProduct(@RequestBody String json){
        log.info("invoke editProduct: " + json);
        Product product = JSONObject.parseObject(json, Product.class);
        JSONObject object = JSONObject.parseObject(json);
        Integer fileId = object.getInteger("fileId");
        if (product.getId() == null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = productService.editProduct(product, fileId);
        return response;
    }
    
    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:37
     * @description: 添加产品
     */
    @PostMapping("/product/add")
    public BaseResponse addProduct(@RequestBody String json){
        log.info("invoke addProduct: " + json);
        Product product = JSONObject.parseObject(json, Product.class);
        JSONObject object = JSONObject.parseObject(json);
        Integer fileId = object.getInteger("fileId");
        if (fileId==null){
            return new BaseResponse(400001, "产品图片未上传");
        }
        BaseResponse response = productService.addProduct(product,fileId);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 11:48
     * @description: 产品列表
     */
    @PostMapping("/product/list")
    public BaseResponse queryProductListByPage(PageRequest request) {
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
        ProductResponse response = productService.queryProductDetail(productId, false);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 12:22
     * @description: 新闻列表
     */
    @PostMapping("/news/list")
    public BaseResponse queryNewsListByPage(PageRequest request){
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
        NewsResponse response = newsService.queryNewsDetail(request.getId(), false);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:12
     * @description: 添加新闻
     */
    @PostMapping("/news/add")
    public BaseResponse addNews(News news, Integer fileId){
//        log.info("invoke addNews: " + json);
//        News news = JSONObject.parseObject(json, News.class);
        if (fileId==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        news.setCreateTime(new Date());
        news.setUpdateTime(new Date());
        BaseResponse response = newsService.addNews(news, fileId);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:14
     * @description: 编辑新闻
     */
    @PostMapping("/news/edit")
    public BaseResponse editNews(News news, Integer fileId){
//        log.info("invoke editNews: " + json);
//        News news = JSONObject.parseObject(json, News.class);
        if (news.getId()==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        news.setUpdateTime(new Date());
        BaseResponse response = newsService.editNews(news, fileId);
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
    public BaseResponse queryVideoListByPage(PageRequest request){
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
    public BaseResponse addVideo(Video video, Integer fileId){
//        log.info("invoke addVideo: " + json);
//        Video video = JSONObject.parseObject(json, Video.class);
        if (fileId==null) {
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = videoService.addVideo(video, fileId);
        return response;
    }

    /**
     * @author Royle.Huang
     * @date 2019/3/3 9:47
     * @description: 编辑视频
     */
    @PostMapping("/video/edit")
    public BaseResponse editVideo(Video video, Integer fileId){
//        log.info("invoke editVideo: " + json);
//        Video video = JSONObject.parseObject(json, Video.class);
        if (video.getId() == null) {
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = videoService.editVideo(video, fileId);
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

package com.stk.website.service.impl;

import com.stk.website.comm.ErrorConstant;
import com.stk.website.comm.Global;
import com.stk.website.dao.mapper.NewsMapper;
import com.stk.website.dao.mapper.TempFileMapper;
import com.stk.website.dao.model.News;
import com.stk.website.dao.model.NewsExample;
import com.stk.website.dao.model.TempFile;
import com.stk.website.dto.NewsResponse;
import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;
import com.stk.website.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    NewsMapper newsMapper;
    @Autowired
    TempFileMapper tempFileMapper;

    @Value("${path.upload.folder}")
    private String uploadFolder;
    @Value("${path.prefix.url}")
    private String urlPrefix;

    @Override
    public PageResponse<News> queryNewsListByPage(PageRequest request) {
        PageResponse<News> response = new PageResponse<>();
        NewsExample example = new NewsExample();
        example.setOffset(request.getLimitStart());
        example.setLimit(request.getRow());
        example.setOrderByClause("id desc");
        List<News> list = newsMapper.selectByExampleWithBLOBs(example);
        long total = newsMapper.countByExample(example);
        if (!list.isEmpty()){
            for (News news : list) {
                news.setIntroduction(news.getIntroduction().replace(System.lineSeparator(),"<br/>").replace("\t", " "));
                news.setImg(urlPrefix+news.getImg());
            }
        }
        response.setPageList(list);
        response.setTotalCount(total);
        response.setTotalPage(Global.getTotalPage(total, request.getRow()));
        return response;
    }

    @Override
    public NewsResponse queryNewsDetail(Integer id, boolean web) {
        NewsResponse response = new NewsResponse();
        News news = newsMapper.selectByPrimaryKey(id);
        if (news==null){
            response.setCode(ErrorConstant.DATABASE_NO_DATA_CODE);
            response.setMsg(ErrorConstant.DATABASE_NO_DATA_MSG);
            return response;
        }
        news.setImg(urlPrefix+news.getImg());
        if (web){
            news.setContent(news.getContent().replace(System.lineSeparator(),"<br/>").replace("\t", " "));
            news.setIntroduction(news.getIntroduction().replace(System.lineSeparator(),"<br/>").replace("\t", " "));
        }
        response.setNews(news);
        return response;
    }

    @Override
    public BaseResponse addNews(News news, Integer fileId) {
        BaseResponse response = new BaseResponse();
        String info = "";
        if (news.getContent().length()>100){
            info = news.getContent().substring(0,100);
        }else {
            info = news.getContent();
        }
        news.setIntroduction(info);
        news.setCreateTime(new Date());
        TempFile tempFile = tempFileMapper.selectByPrimaryKey(fileId);
        news.setImg(tempFile.getFilePath());
        newsMapper.insert(news);
        tempFileMapper.deleteByPrimaryKey(fileId);
        return response;
    }

    @Override
    public BaseResponse editNews(News news, Integer fileId) {
        BaseResponse response = new BaseResponse();
        News bean = newsMapper.selectByPrimaryKey(news.getId());
        if (bean==null){
            response.setCode(ErrorConstant.DATABASE_NO_DATA_CODE);
            response.setMsg(ErrorConstant.DATABASE_NO_DATA_MSG);
            return response;
        }
        if (news.getImg().contains(urlPrefix)){
            news.setImg(news.getImg().replace(urlPrefix,""));
        }
        String oldFilePath = bean.getImg();
        if (fileId != null){
            TempFile tempFile = tempFileMapper.selectByPrimaryKey(fileId);
            news.setImg(tempFile.getFilePath());
            if (tempFile!=null){
                File file = new File(uploadFolder+oldFilePath);
                file.delete();
            }
        }
        String info = "";
        if (news.getContent().length()>100){
            info = news.getContent().substring(0,100);
        }else {
            info = news.getContent();
        }
        news.setIntroduction(info);
        news.setCreateTime(bean.getCreateTime());
        newsMapper.updateByPrimaryKeyWithBLOBs(news);
        tempFileMapper.deleteByPrimaryKey(fileId);
        return response;
    }

    @Override
    public BaseResponse deleteNews(Integer id) {
        BaseResponse response = new BaseResponse();
        News bean = newsMapper.selectByPrimaryKey(id);
        if (bean!=null){
            String oldFilePath = bean.getImg();
            File file = new File(uploadFolder+oldFilePath);
            file.delete();
        }
        newsMapper.deleteByPrimaryKey(id);
        return response;
    }
}

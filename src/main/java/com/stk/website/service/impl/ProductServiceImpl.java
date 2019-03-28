package com.stk.website.service.impl;

import com.stk.website.comm.ErrorConstant;
import com.stk.website.comm.Global;
import com.stk.website.dao.mapper.ProductDetailMapper;
import com.stk.website.dao.mapper.ProductMapper;
import com.stk.website.dao.mapper.TempFileMapper;
import com.stk.website.dao.model.*;
import com.stk.website.dto.HomeProductResponse;
import com.stk.website.dto.ProductResponse;
import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;
import com.stk.website.exception.ServiceException;
import com.stk.website.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductDetailMapper productDetailMapper;
    @Autowired
    TempFileMapper tempFileMapper;

    @Override
    public PageResponse<Product> queryProductListByPage(PageRequest request) {
        PageResponse<Product> response = new PageResponse<>();
        ProductExample example = new ProductExample();
        example.setOffset(request.getLimitStart());
        example.setLimit(request.getRow());
        example.setOrderByClause("id");
        List<Product> list = productMapper.selectByExample(example);
        long total = productMapper.countByExample(example);
        if (!list.isEmpty()){
            for (Product product : list) {
                String s = product.getDesc().replace(System.lineSeparator(), "<br/>").replace("\t", " ");
                product.setDesc(s);
            }
        }
        response.setPageList(list);
        response.setTotalCount(total);
        response.setTotalPage(Global.getTotalPage(total, request.getRow()));
        return response;
    }

    @Override
    public ProductResponse queryProductDetail(Integer productId) {
        ProductResponse response = new ProductResponse();
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product == null){
            response.setCode(ErrorConstant.DATABASE_NO_DATA_CODE);
            response.setMsg(ErrorConstant.DATABASE_NO_DATA_MSG);
            return response;
        }
        product.setDesc(product.getDesc().replace(System.lineSeparator(), "<br/>").replace("\t", " "));
        ProductDetailExample example = new ProductDetailExample();
        ProductDetailExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        example.setOrderByClause("`index`, id");
        List<ProductDetail> list = productDetailMapper.selectByExampleWithBLOBs(example);
        for (ProductDetail productDetail : list) {
            productDetail.setFunctionDesc(productDetail.getFunctionDesc().replace(System.lineSeparator(), "<br/>").replace("\t", " "));
        }
        product.setDetails(list);
        response.setProduct(product);
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse addProduct(Product product, Integer fileId) {
        BaseResponse response = new BaseResponse();
        productMapper.insert(product);
        List<ProductDetail> list = product.getDetails();
        if (list != null && !list.isEmpty()){
            for (ProductDetail productDetail : list) {
                productDetail.setProductId(product.getId());
                productDetailMapper.insert(productDetail);
            }
        }
        tempFileMapper.deleteByPrimaryKey(fileId);
        return response;
    }

    @Override
    @Transactional
    public BaseResponse editProduct(Product product, Integer fileId) {
        BaseResponse response = new BaseResponse();
        Product bean = productMapper.selectByPrimaryKey(product.getId());
        if (bean == null){
            response.setCode(ErrorConstant.DATABASE_NO_DATA_CODE);
            response.setMsg(ErrorConstant.DATABASE_NO_DATA_MSG);
            return response;
        }
        String oldFilePath = bean.getImg();
        if (fileId != null){
            TempFile tempFile = tempFileMapper.selectByPrimaryKey(fileId);
            if (tempFile!=null){
                File file = new File(oldFilePath);
                file.delete();
            }
        }
        productMapper.updateByPrimaryKey(product);
        List<ProductDetail> list = product.getDetails();
        if (list != null && !list.isEmpty()){
            for (ProductDetail productDetail : list) {
                if (productDetail.getId()==null){
                    productDetail.setProductId(product.getId());
                    productDetailMapper.insert(productDetail);
                }else {
                    productDetailMapper.updateByPrimaryKeyWithBLOBs(productDetail);
                }
            }
        }
        tempFileMapper.deleteByPrimaryKey(fileId);
        return response;
    }

    @Override
    public BaseResponse addProductDetail(ProductDetail productDetail) {
        BaseResponse response = new BaseResponse();
        productDetailMapper.insert(productDetail);
        return response;
    }

    @Override
    public BaseResponse editProductDetail(ProductDetail productDetail) {
        BaseResponse response = new BaseResponse();
        if (productDetail.getId()==null){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        ProductDetail detail = productDetailMapper.selectByPrimaryKey(productDetail.getId());
        if (detail==null){
            response.setCode(ErrorConstant.DATABASE_NO_DATA_CODE);
            response.setMsg(ErrorConstant.DATABASE_NO_DATA_MSG);
            return response;
        }
        productDetailMapper.updateByPrimaryKeyWithBLOBs(productDetail);
        return response;
    }

    @Override
    public BaseResponse deleteProductDetail(Integer id) {
        BaseResponse response = new BaseResponse();
        productDetailMapper.deleteByPrimaryKey(id);
        return response;
    }

    @Override
    @Transactional
    public BaseResponse deleteProduct(Integer id) {
        BaseResponse response = new BaseResponse();
        //删除产品详情
        ProductDetailExample example = new ProductDetailExample();
        ProductDetailExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(id);
        productDetailMapper.deleteByExample(example);
        Product bean = productMapper.selectByPrimaryKey(id);
        if (bean!=null){
            String oldFilePath = bean.getImg();
            File file = new File(oldFilePath);
            file.delete();
        }
        //删除产品
        productMapper.deleteByPrimaryKey(id);
        return response;
    }

    @Override
    public HomeProductResponse queryProductListHome() {
        HomeProductResponse response = new HomeProductResponse();
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andShowHomeEqualTo(1);
        List<Product> list = productMapper.selectByExample(example);
        response.setProducts(list);
        return response;
    }
}

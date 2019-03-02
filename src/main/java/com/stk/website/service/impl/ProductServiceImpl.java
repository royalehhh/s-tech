package com.stk.website.service.impl;

import com.stk.website.comm.ErrorConstant;
import com.stk.website.comm.Global;
import com.stk.website.dao.mapper.ProductDetailMapper;
import com.stk.website.dao.mapper.ProductMapper;
import com.stk.website.dao.model.Product;
import com.stk.website.dao.model.ProductDetail;
import com.stk.website.dao.model.ProductDetailExample;
import com.stk.website.dao.model.ProductExample;
import com.stk.website.dto.ProductResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;
import com.stk.website.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductDetailMapper productDetailMapper;

    @Override
    public PageResponse<Product> queryProductListByPage(PageRequest request) {
        PageResponse<Product> response = new PageResponse<>();
        ProductExample example = new ProductExample();
        example.setOffset(request.getLimitStart());
        example.setLimit(request.getRow());
        example.setOrderByClause("id");
        List<Product> list = productMapper.selectByExample(example);
        long total = productMapper.countByExample(example);
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
        ProductDetailExample example = new ProductDetailExample();
        ProductDetailExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId);
        example.setOrderByClause("`index`, id");
        List<ProductDetail> list = productDetailMapper.selectByExampleWithBLOBs(example);
        product.setDetails(list);
        response.setProduct(product);
        return response;
    }
}

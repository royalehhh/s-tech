package com.stk.website.service;

import com.stk.website.dao.model.Product;
import com.stk.website.dto.ProductResponse;
import com.stk.website.dto.inner.PageRequest;
import com.stk.website.dto.inner.PageResponse;

public interface IProductService {

    /**
     * @author Royle.Huang
     * @date 2019/2/24 11:44
     * @description: 查询产品列表
     */
    PageResponse<Product> queryProductListByPage(PageRequest request);

    /**
     * @author Royle.Huang
     * @date 2019/3/2 11:55
     * @description: 产品详情
     */
    ProductResponse queryProductDetail(Integer productId);
}

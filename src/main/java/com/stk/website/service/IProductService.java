package com.stk.website.service;

import com.stk.website.dao.model.Product;
import com.stk.website.dao.model.ProductDetail;
import com.stk.website.dto.ProductResponse;
import com.stk.website.dto.inner.BaseResponse;
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

    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:42
     * @description: 添加商品
     */
    BaseResponse addProduct(Product product);

    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:54
     * @description: 编辑产品
     */
    BaseResponse editProduct(Product product);

    /**
     * @author Royle.Huang
     * @date 2019/3/2 15:59
     * @description: 添加产品详情
     */
    BaseResponse addProductDetail(ProductDetail productDetail);

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:02
     * @description: 编辑产品详情
     */
    BaseResponse editProductDetail(ProductDetail productDetail);

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:07
     * @description: 删除产品详情
     */
    BaseResponse deleteProductDetail(Integer id);

    /**
     * @author Royle.Huang
     * @date 2019/3/2 16:09
     * @description: 删除产品
     */
    BaseResponse deleteProduct(Integer id);
}

package com.stk.website.dto;

import com.stk.website.dao.model.Product;
import com.stk.website.dto.inner.BaseResponse;
import lombok.Data;

@Data
public class ProductResponse extends BaseResponse {

    private Product product;
}

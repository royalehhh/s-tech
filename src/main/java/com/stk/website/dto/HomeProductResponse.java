package com.stk.website.dto;

import com.stk.website.dao.model.Product;
import com.stk.website.dto.inner.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class HomeProductResponse extends BaseResponse {

    List<Product> products;
}

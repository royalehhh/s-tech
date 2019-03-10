package com.stk.website.dto;

import com.stk.website.dto.inner.BaseResponse;
import lombok.Data;

@Data
public class LoginResponse extends BaseResponse {

    String token;
    String userName;
}

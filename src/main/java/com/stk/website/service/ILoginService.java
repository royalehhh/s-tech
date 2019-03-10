package com.stk.website.service;

import com.stk.website.dto.LoginResponse;
import com.stk.website.dto.inner.BaseResponse;


public interface ILoginService {

    /**
     * @author Royle.Huang
     * @date 2019/2/21 22:09
     * @description: 登录
     */
    LoginResponse login(String name, String pwd);
}

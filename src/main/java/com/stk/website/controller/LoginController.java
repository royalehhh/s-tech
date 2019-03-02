package com.stk.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.stk.website.comm.ErrorConstant;
import com.stk.website.comm.Global;
import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.exception.ServiceException;
import com.stk.website.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Royle.Huang
 * @date 2019/2/21 21:20
 * @description: 登录
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    ILoginService loginService;

    @PostMapping("login")
    public BaseResponse login(HttpServletRequest request, @RequestBody String json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String name = (String) jsonObject.get("name");
        String pwd = (String) jsonObject.get("pwd");

        if (name==null || "".equals(name)){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        if (pwd==null || "".equals(pwd)){
            throw new ServiceException(ErrorConstant.PARAM_INCOMPLETE_CODE, ErrorConstant.PARAM_INCOMPLETE_MSG);
        }
        BaseResponse response = loginService.login(name, pwd);
        if (response.getCode() == 0){
            String token = UUID.randomUUID().toString().replace("-","");
            Global.tokenMap.put(name, token);
            request.getSession().setAttribute("token", token);
        }
        return response;
    }
}

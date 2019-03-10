package com.stk.website.service.impl;

import com.stk.website.comm.ErrorConstant;
import com.stk.website.comm.Global;
import com.stk.website.dao.mapper.UserMapper;
import com.stk.website.dao.model.User;
import com.stk.website.dao.model.UserExample;
import com.stk.website.dto.LoginResponse;
import com.stk.website.dto.inner.BaseResponse;
import com.stk.website.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Royle.Huang
 * @date 2019/2/21 20:19
 * @description: 登录service
 */

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public LoginResponse login(String name, String pwd) {
        LoginResponse response = new LoginResponse();
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(name).andPasswordEqualTo(pwd);
        List<User> list = userMapper.selectByExample(example);
        if (list.isEmpty()){
            response.setCode(ErrorConstant.USER_NOT_EXIST_CODE);
            response.setMsg(ErrorConstant.USER_NOT_EXIST_MSG);
        }else {
            String token = UUID.randomUUID().toString().replace("-","");
            Global.tokenMap.put(name, token);
            response.setToken(token);
            response.setUserName(name);
            response.setCode(0);
            response.setMsg("login success");
        }
        return response;
    }
}

package com.stk.website.dto.inner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author royle.huang
 * @Date 2019/1/31 14:39
 * @Description 返回基类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private int code=0;

    private String msg;
}

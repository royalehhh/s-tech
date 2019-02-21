package com.stk.website.dto.inner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author royle.huang
 * @Date 2019/1/31 14:37
 * @Description 请求基类
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {
    private String token;
}

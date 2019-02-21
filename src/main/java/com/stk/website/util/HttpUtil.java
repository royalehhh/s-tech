package com.stk.website.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * @Author royle.huang
 * @Date 2019/2/19 18:54
 * @Description 发送请求
 **/
@Slf4j
public class HttpUtil {



    public static String commHttpRequest(String urlPath, HttpMethod httpMethod, HttpEntity<?> requestEntity, Class<String> responseType) {
        ResponseEntity<String> response = null;
        String responseString = null;
        try {
            log.info("发送http请求url:{}, method:{}, request:{}", urlPath, httpMethod, JSONObject.toJSONString(requestEntity));
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.exchange(urlPath, httpMethod, requestEntity, responseType);
            log.info("接收http请求response:{}", JSONObject.toJSONString(response));
        } catch (HttpStatusCodeException e) {
            log.info("发送http请求错误", e);
            responseString = e.getResponseBodyAsString();
        } catch (Exception e) {
            log.info("发送http请求错误", e);
        }
        //打印header错误码
        if (response != null && response.getHeaders().containsKey("statusCode")) {
            log.warn("请求返回码：{}", JSONObject.toJSONString(response.getHeaders().get("statusCode")));
        }
        if (response != null) {
            responseString = response.getBody();
        }
        if (responseString == null) {
            responseString = "";
        }
        return responseString;
    }
}

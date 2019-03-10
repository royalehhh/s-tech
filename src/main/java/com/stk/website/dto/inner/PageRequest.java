package com.stk.website.dto.inner;

import lombok.Data;

/**
 * @Author royle.huang
 * @Date 2019/1/31 14:42
 * @Description 有分页需求的请求基类
 **/

@Data
public class PageRequest extends BaseRequest {

    /**分页大小**/
    private Integer row;
    /**当前页**/
    private Integer page;
    private Integer limit;
    private String sort;//排序字段
    private String order;//排序方式
    private String keyWord;//关键字
    public long getLimitStart(){
        return (page-1)*row;
    }

}

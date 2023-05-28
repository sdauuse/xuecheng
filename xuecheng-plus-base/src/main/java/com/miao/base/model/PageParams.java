package com.miao.base.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author Mr.M
 * @version 1.0
 * @description 分页查询通用参数
 * @date 2022/9/6 14:02
 */
@Data
@ToString
public class PageParams {

    //当前页码
    private Long pageNo = 1L;

    //每页记录数默认值
    private Long pageSize = 10L;

    public PageParams() {

    }

    public PageParams(long pageNo, long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

}
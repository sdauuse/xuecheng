package com.miao.content.dto;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程查询参数Dto
 * @date 2022/9/6 14:36
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryCourseParamsDto {

    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;

}

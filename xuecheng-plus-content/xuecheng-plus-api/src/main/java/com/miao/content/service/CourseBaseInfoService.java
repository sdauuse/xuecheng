package com.miao.content.service;

import com.miao.base.model.PageParams;
import com.miao.base.model.PageResult;
import com.miao.content.dto.AddCourseDto;
import com.miao.content.dto.CourseBaseInfoDto;
import com.miao.content.dto.EditCourseDto;
import com.miao.content.dto.QueryCourseParamsDto;
import com.miao.content.model.po.CourseBase;

public interface CourseBaseInfoService {

    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto courseParamsDto);

    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    CourseBaseInfoDto selectCourseBaseInfoById(Long courseId);

    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto);
}

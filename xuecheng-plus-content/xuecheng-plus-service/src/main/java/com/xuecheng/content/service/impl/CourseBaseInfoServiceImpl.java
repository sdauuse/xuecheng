//package com.xuecheng.content.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.miao.base.model.PageParams;
//import com.miao.base.model.PageResult;
//import com.miao.content.dto.QueryCourseParamsDto;
//import com.miao.content.model.po.CourseBase;
//import com.xuecheng.content.mapper.CourseBaseMapper;
//import com.xuecheng.content.service.CourseBaseInfoService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
//
//    @Autowired
//    private CourseBaseMapper courseBaseMapper;
//
//    @Override
//    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto courseParamsDto) {
//
//        //构造查询条件
//        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper();
//        queryWrapper.like(StringUtils.isNotEmpty(courseParamsDto.getCourseName()), CourseBase::getName, courseParamsDto.getCourseName());
//        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, courseParamsDto.getAuditStatus());
//        queryWrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getPublishStatus()), CourseBase::getStatus, courseParamsDto.getPublishStatus());
//
//        //分页查询
//        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
//        Page<CourseBase> result = courseBaseMapper.selectPage(page, queryWrapper);
//
//        //返回结果
//        List<CourseBase> records = result.getRecords();
//        long total = result.getTotal();
//
//        return new PageResult<CourseBase> (records, total, pageParams.getPageNo(), pageParams.getPageSize());
//    }
//}

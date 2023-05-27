package com.miao.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.miao.base.exception.XueChengPlusException;
import com.miao.content.mapper.CourseTeacherMapper;
import com.miao.content.model.po.CourseTeacher;
import com.miao.content.model.po.Teachplan;
import com.miao.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CourseTeacherServiceImpl implements CourseTeacherService {

    @Autowired
    private CourseTeacherMapper courseTeacherMapper;

    @Override
    public CourseTeacher getCourseTeacher(CourseTeacher courseTeacher) {
        return null;
    }

    @Override
    @Transactional
    public CourseTeacher saveAndUpdateCourseTeacher(CourseTeacher courseTeacher) {

        //判断是新增还是修改
        if (courseTeacher.getId() == null) {
            //新增
            CourseTeacher ct = courseTeacherMapper.selectById(courseTeacher.getCourseId());
            courseTeacherMapper.insert(courseTeacher);
            return courseTeacherMapper.selectById(courseTeacher.getCourseId());
        }else {
            //修改
            courseTeacherMapper.updateById(courseTeacher);
            return courseTeacherMapper.selectById(courseTeacher.getId());
        }
    }

    @Override
    @Transactional
    public CourseTeacher updateCourseTeacher(CourseTeacher courseTeacher) {
        courseTeacherMapper.updateById(courseTeacher);
        return courseTeacherMapper.selectById(courseTeacher.getId());
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId, Long id) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseId);
        queryWrapper.eq(CourseTeacher::getId, id);

        courseTeacherMapper.delete(queryWrapper);
    }
}

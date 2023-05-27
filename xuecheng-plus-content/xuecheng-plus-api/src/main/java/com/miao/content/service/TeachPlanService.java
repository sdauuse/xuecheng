package com.miao.content.service;

import com.miao.content.dto.SaveTeachplanDto;
import com.miao.content.dto.TeachplanDto;
import com.miao.content.model.po.CourseTeacher;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程计划管理相关接口
 * @date 2023/2/14 12:10
 */
public interface TeachPlanService {

    /**
     * 根据课程id查询课程计划
     * @param courseId 课程计划
     * @return
     */
    public List<TeachplanDto> findTeachPlanTree(Long courseId);

    void saveAndUpdateTeachPlan(SaveTeachplanDto teachplan);

    void deleteTeachplan(Long courseId);

    void moveUp(Long courseId);

    void moveDown(Long courseId);

    List<CourseTeacher> selectTeachersByCourseId(Long courseId);

    void deleteWithCourseId(Long courseId);
}

package com.miao.content.service;

import com.miao.content.model.po.CourseTeacher;

public interface CourseTeacherService {
    CourseTeacher getCourseTeacher(CourseTeacher courseTeacher);

    CourseTeacher saveAndUpdateCourseTeacher(CourseTeacher courseTeacher);

    CourseTeacher updateCourseTeacher(CourseTeacher courseTeacher);

    void deleteCourse(Long courseId, Long id);
}

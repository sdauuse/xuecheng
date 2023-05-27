package com.miao.content.api;

import com.miao.content.model.po.CourseTeacher;
import com.miao.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "课程老师管理接口", tags = "课程老师管理接口")
@RestController
public class CourseTeacherController {

    @Autowired
    private CourseTeacherService courseTeacherService;

    @ApiOperation("新增老师")
    @PostMapping("/courseTeacher")
    public CourseTeacher courseTeacher(@RequestBody CourseTeacher courseTeacher) {

        return courseTeacherService.saveAndUpdateCourseTeacher(courseTeacher);
    }

    @ApiOperation("修改教师")
    @PutMapping("/courseTeacher")
    public CourseTeacher updateCourseTeacher(@RequestBody CourseTeacher courseTeacher) {

        return courseTeacherService.updateCourseTeacher(courseTeacher);
    }

    @ApiOperation("删除教师")
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void deleteCourse(@PathVariable("courseId") Long courseId, @PathVariable("id") Long id) {
        courseTeacherService.deleteCourse(courseId, id);
    }


}

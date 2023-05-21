package com.miao.content.api;

import com.miao.base.model.PageParams;
import com.miao.base.model.PageResult;
import com.miao.content.dto.QueryCourseParamsDto;
import com.miao.content.model.po.CourseBase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class CourseBaseInfoController {

    @RequestMapping("/course/list")
    public PageResult<CourseBase> list(PageParams params, @RequestBody QueryCourseParamsDto queryCourseParamsDto) {

        return null;
    }
}

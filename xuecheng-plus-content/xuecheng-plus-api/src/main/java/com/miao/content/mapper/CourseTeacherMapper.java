package com.miao.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miao.content.model.po.CourseTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 课程-教师关系表 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface CourseTeacherMapper extends BaseMapper<CourseTeacher> {

}

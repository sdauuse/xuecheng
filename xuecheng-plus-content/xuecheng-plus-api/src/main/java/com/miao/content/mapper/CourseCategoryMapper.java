package com.miao.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miao.content.dto.CourseCategoryTreeDto;
import com.miao.content.model.po.CourseCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {


    public List<CourseCategoryTreeDto> selectTreeNodes(String id);
}

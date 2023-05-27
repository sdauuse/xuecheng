package com.miao.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miao.content.dto.TeachplanDto;
import com.miao.content.model.po.Teachplan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 */
@Mapper
public interface TeachPlanMapper extends BaseMapper<Teachplan> {

    //课程计划查询
    public List<TeachplanDto> selectTreeNodes(Long courseId);

    @Select("SELECT *\n" +
            "        FROM teachplan\n" +
            "        WHERE parentid = #{parentId}\n" +
            "        and course_id = #{courseId}\n" +
            "        ORDER BY orderby ASC")
    List<Teachplan> selectListOrderByAsc(@Param("parentId") Long parentId,@Param("courseId") Long courseId);

    @Select("SELECT *\n" +
            "        FROM teachplan\n" +
            "        WHERE parentid = #{parentId}\n" +
            "        and course_id = #{courseId}\n" +
            "        ORDER BY orderby DESC")
    List<Teachplan> selectListOrderByDesc(@Param("parentId") Long parentId,@Param("courseId") Long courseId);
}

package com.miao.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.miao.content.dto.TeachplanDto;
import com.miao.content.model.po.Teachplan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 */
@Mapper
public interface TeachPlanMapper extends BaseMapper<Teachplan> {

    //课程计划查询
    public List<TeachplanDto> selectTreeNodes(Long courseId);

}

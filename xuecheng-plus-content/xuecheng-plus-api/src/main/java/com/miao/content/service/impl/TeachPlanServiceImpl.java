package com.miao.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.miao.content.dto.SaveTeachplanDto;
import com.miao.content.dto.TeachplanDto;
import com.miao.content.mapper.TeachPlanMapper;
import com.miao.content.model.po.Teachplan;
import com.miao.content.service.TeachPlanService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class TeachPlanServiceImpl implements TeachPlanService {

    @Autowired
    private TeachPlanMapper teachplanMapper;

    @Override
    public List<TeachplanDto> findTeachPlanTree(Long courseId) {

        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(courseId);
        return teachplanDtos;
    }

    private Integer getTeachplanCount(Long courseId, Long parentId) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Teachplan::getCourseId, courseId).eq(Teachplan::getParentid, parentId);
        Integer n = teachplanMapper.selectCount(queryWrapper);
        return n;
    }

    @Transactional
    @Override
    public void saveAndUpdateTeachPlan(SaveTeachplanDto teachplanDto) {
        //通过课程计划id判断是新增和修改
        Long teachplanId = teachplanDto.getId();
        if (teachplanId == null) {
            //新增

            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(teachplanDto, teachplan);

            Long courseId = teachplanDto.getCourseId();
            Long parentId = teachplanDto.getParentid();
            int n = getTeachplanCount(courseId, parentId);
            teachplan.setOrderby(n + 1);
            //确定排序字段，找到它的同级节点个数，排序字段就是个数加1  select count(1) from teachplan where course_id=117 and parentid=268
            teachplanMapper.insert(teachplan);

        } else {
            //修改
            Teachplan teachplan = teachplanMapper.selectById(teachplanId);

            //将参数复制到teachplan
            BeanUtils.copyProperties(teachplanDto, teachplan);
            teachplanMapper.updateById(teachplan);
        }
    }
}

package com.miao.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.miao.base.exception.XueChengPlusException;
import com.miao.content.dto.SaveTeachplanDto;
import com.miao.content.dto.TeachplanDto;
import com.miao.content.mapper.CourseBaseMapper;
import com.miao.content.mapper.CourseMarketMapper;
import com.miao.content.mapper.CourseTeacherMapper;
import com.miao.content.mapper.TeachPlanMapper;
import com.miao.content.model.po.CourseBase;
import com.miao.content.model.po.CourseTeacher;
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
@SuppressWarnings("all")
public class TeachPlanServiceImpl implements TeachPlanService {

    @Autowired
    private TeachPlanMapper teachplanMapper;

    @Autowired
    private CourseTeacherMapper courseTeacherMapper;

    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Autowired
    private CourseMarketMapper courseMarketMapper;


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

    @Transactional()
    @Override
    public void deleteTeachplan(Long courseId) {
        Teachplan teachplan = teachplanMapper.selectById(courseId);
        if (teachplan.getGrade() == 2) {
            //二级标题可以删除
            teachplanMapper.deleteById(courseId);
        } else {
            //一级标题要检查是否有子标题
            LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Teachplan::getParentid, courseId);
            Integer n = teachplanMapper.selectCount(queryWrapper);
            if (n == 0) {
                teachplanMapper.deleteById(courseId);
            } else {
                XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
            }
        }
    }

    @Override
    @Transactional
    public void moveUp(Long courseId) {

        Teachplan teachplan = teachplanMapper.selectById(courseId);

        List<Teachplan> teachplans = teachplanMapper.selectListOrderByDesc(teachplan.getParentid(), teachplan.getCourseId());
        boolean flag = false;
        for (Teachplan t : teachplans) {
            if (t.getOrderby() < teachplan.getOrderby()) {
                int temp = teachplan.getOrderby();
                teachplan.setOrderby(t.getOrderby());
                t.setOrderby(temp);
                teachplanMapper.updateById(t);
                teachplanMapper.updateById(teachplan);
                flag = true;
                return;
            }
        }

        if (!flag) {
            XueChengPlusException.cast("上移失败");
        }

    }

    @Transactional
    @Override
    public void moveDown(Long courseId) {
        Teachplan teachplan = teachplanMapper.selectById(courseId);

        List<Teachplan> teachplans = teachplanMapper.selectListOrderByAsc(teachplan.getParentid(), teachplan.getCourseId());
        boolean flag = false;
        for (Teachplan t : teachplans) {
            if (t.getOrderby() > teachplan.getOrderby()) {
                int temp = teachplan.getOrderby();
                teachplan.setOrderby(t.getOrderby());
                t.setOrderby(temp);
                teachplanMapper.updateById(t);
                teachplanMapper.updateById(teachplan);
                flag = true;
                return;
            }
        }

        if (!flag) {
            XueChengPlusException.cast("下移失败");
        }
    }

    @Override
    public List<CourseTeacher> selectTeachersByCourseId(Long courseId) {

        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseId);
        List<CourseTeacher> courseTeacher = courseTeacherMapper.selectList(queryWrapper);

        return courseTeacher;
    }


    @Transactional
    @Override
    public void deleteWithCourseId(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseTeacher::getCourseId, courseId);

        LambdaQueryWrapper<Teachplan> teachplanQueryWrapper = new LambdaQueryWrapper<>();
        teachplanQueryWrapper.eq(Teachplan::getCourseId, courseId);

        courseBaseMapper.deleteById(courseId);
        courseTeacherMapper.delete(queryWrapper);
        teachplanMapper.delete(teachplanQueryWrapper);
        courseMarketMapper.deleteById(courseId);
    }
}

package com.miao.content.api;

import com.miao.content.dto.SaveTeachplanDto;
import com.miao.content.dto.TeachplanDto;
import com.miao.content.service.TeachPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "课程信息管理接口", tags = "课程信息管理接口")
@RestController
public class TeachPlanController {

    @Autowired
    private TeachPlanService teachPlanService;


    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId", name = "课程Id", required = true, dataType = "Long", paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable("courseId") Long courseId) {

        return teachPlanService.findTeachPlanTree(courseId);
    }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachplan(@RequestBody SaveTeachplanDto teachplan) {

        teachPlanService.saveAndUpdateTeachPlan(teachplan);
    }
}

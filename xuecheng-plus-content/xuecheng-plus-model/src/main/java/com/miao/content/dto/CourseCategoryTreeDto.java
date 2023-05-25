package com.miao.content.dto;

import com.miao.content.model.po.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 课程分类
 * </p>
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {

    //子节点
    List<CourseCategoryTreeDto> childrenTreeNodes;

}

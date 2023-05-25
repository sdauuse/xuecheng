package com.miao.content.service.impl;

import com.miao.content.dto.CourseCategoryTreeDto;
import com.miao.content.mapper.CourseCategoryMapper;
import com.miao.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {

        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);

        //map中的key 取id，value就是原来的对象的值，如果遇到有两个key，选第二个key作为map的key
        //filter的意思是满足条件的则保留,filter(item -> !id.equals(item.getId()))意思是把根节点排除
        Map<String, CourseCategoryTreeDto> mapTemp = courseCategoryTreeDtos.stream().filter(item -> !id.equals(item.getId())).collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        //从头遍历courseCategoryTreeDtos

        List<CourseCategoryTreeDto> courseCategoryList = new ArrayList<>();
        courseCategoryTreeDtos.stream().forEach(item -> {
            if (item.getParentid().equals(id)) {
                courseCategoryList.add(item);
            }
            CourseCategoryTreeDto courseCategoryTreeDto = mapTemp.get(item.getParentid());
            if (courseCategoryTreeDto != null) {
                if (courseCategoryTreeDto.getChildrenTreeNodes() == null) {
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }

                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }
        });

        return courseCategoryList;
    }
}

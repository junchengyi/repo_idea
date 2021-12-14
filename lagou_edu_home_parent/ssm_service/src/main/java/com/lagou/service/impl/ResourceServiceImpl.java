package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceCategoryVo;
import com.lagou.domain.ResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(allResource);
        return pageInfo;


    }

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> resourceCategoryList = resourceMapper.findAllResourceCategory();
        return resourceCategoryList;
    }

    @Override
    public void saveResourceCategory(ResourceCategoryVo resourceCategoryVo) {
        ResourceCategory resourceCategory = new ResourceCategory();
        resourceCategory.setName(resourceCategoryVo.getName());
        resourceCategory.setSort(resourceCategoryVo.getSort());

        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);

        resourceCategory.setCreatedBy("System");
        resourceCategory.setUpdatedBy("System");

        resourceMapper.saveResourceCategory(resourceCategory);
    }

    @Override
    public void updateResourceCategory(ResourceCategoryVo resourceCategoryVo) {
        ResourceCategory resourceCategory = new ResourceCategory();
        resourceCategory.setId(resourceCategoryVo.getId());
        resourceCategory.setName(resourceCategoryVo.getName());
        resourceCategory.setSort(resourceCategoryVo.getSort());

        Date date = new Date();
        resourceCategory.setUpdatedTime(date);

        resourceCategory.setUpdatedBy("System");

        resourceMapper.updateResourceCategory(resourceCategory);

    }

    @Override
    public void deleteResourceCategory(Integer id) {
        resourceMapper.deleteResourceCategory(id);
    }
}

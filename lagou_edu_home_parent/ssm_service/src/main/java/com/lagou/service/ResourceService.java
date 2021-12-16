package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceCategoryVo;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceService {

    public PageInfo<Resource> findAllResource(ResourceVo resourceVo);



    void saveResource(Resource resource);

    void updateResource(Resource resource);

    void deleteResource(Integer id);


    /*资源分类信息查询*/
    public List<ResourceCategory> findAllResourceCategory();

    /*添加资源分类*/
    public void saveResourceCategory(ResourceCategoryVo resourceCategoryVo);
    /*更新资源分类*/
    public void updateResourceCategory(ResourceCategoryVo resourceCategoryVo);

    /*删除资源分类*/
    public void deleteResourceCategory(Integer id);
}

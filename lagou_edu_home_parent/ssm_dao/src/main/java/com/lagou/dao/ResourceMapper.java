package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceCategoryVo;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {



    public List<Resource> findAllResource(ResourceVo resourceVo);


    /*资源分类信息查询*/
    public List<ResourceCategory> findAllResourceCategory();


    /*新增修改资源分类*/
    public void saveResourceCategory(ResourceCategory resourceCategory);
    public void updateResourceCategory(ResourceCategory resourceCategory);
    /*删除资源分类*/
    public void deleteResourceCategory(Integer id);


    void saveResource(Resource resource);

    void updateResource(Resource resource);

    void deleteResource(Integer id);


}

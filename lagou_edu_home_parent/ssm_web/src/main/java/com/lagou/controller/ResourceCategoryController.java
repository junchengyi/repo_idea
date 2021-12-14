package com.lagou.controller;


import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceCategoryVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceService resourceService;


    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {
        List<ResourceCategory> ResourceCategoryList = resourceService.findAllResourceCategory();
        return new ResponseResult(true, 200, "响应成功", ResourceCategoryList);
    }

    /*http://localhost:8080/ssm_web/ResourceCategory/saveOrUpdateResourceCategory*/
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategoryVo resourceCategoryVo) {
        if (resourceCategoryVo.getId()!=null){
            resourceService.updateResourceCategory(resourceCategoryVo);
            return new ResponseResult(true, 200, "响应成功", resourceCategoryVo);
        }   else {
            resourceService.saveResourceCategory(resourceCategoryVo);
            return new ResponseResult(true, 200, "响应成功", resourceCategoryVo);
        }
    }


    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceService.deleteResourceCategory(id);
        return new ResponseResult(true, 200, "响应成功", null);
    }

}

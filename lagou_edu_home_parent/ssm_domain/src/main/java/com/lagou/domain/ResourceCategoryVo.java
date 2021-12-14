package com.lagou.domain;

public class ResourceCategoryVo {


    /*//新增
{
	name: "作业管理",
	sort: 1
}

//修改
{
    id: 10,
    name: "家庭作业管理",
    sort: "2"
}*/
    private String name;

    private Integer id;

    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}

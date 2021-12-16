package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {
    /*查询角色列表*/
    public List<Role> findAllRole(Role role);
    /*
        根据角色ID查询菜单信息
    */
    List<String> findMenuByRoleId(Integer roleId);

    /*为角色分配菜单*/
    public void RoleContextMenu(RoleMenuVo roleMenuVo);

    /*删除角色*/
    public void deleteRole(Integer id);

    /*查询角色资源分类及资源信息*/
    public List<ResourceCategory> findRoleResourceCategoryById(Integer roleId);

    /*为角色分配资源*/
    public void RoleContextResourceCategory(RoleResourceVo roleResourceVo);

    public void saveRole(Role role);

    public void updateRole(Role role);
}

package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface roleMapper {
    /*查询角色列表*/
    public List<Role> findAllRole(Role role);
    /*
        根据角色ID查询菜单信息
    */
    List<String> findMenuByRoleId(Integer roleId);

    /*删除角色关联菜单*/
    public void deleteRoleContextMenu(Integer roleId);

    /*为角色分配菜单列表*/
    public void roleMenuRelation(Role_menu_relation role_menu_relation);

    /*删除角色*/
    public void deleteRole(Integer id);
    /*查询当前角色的所有资源分类信息*/
    public List<ResourceCategory> findRoleAllResourceCategory(Integer roleId);
    /*查询当前角色的所有资源信息*/
    public List<Resource> findRoleAllResource(Integer categoryId);

    /*删除角色关联菜单*/
    public void deleteRoleResource(Integer roleId);

    /*为角色分配菜单列表*/
    public void roleResourceRelation(RoleResourceRelation roleResourceRelation);

    public void saveRole(Role role);

    public void updateRole(Role role);
}

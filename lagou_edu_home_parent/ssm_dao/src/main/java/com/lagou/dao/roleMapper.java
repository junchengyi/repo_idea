package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

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

}

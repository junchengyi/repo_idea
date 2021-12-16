package com.lagou.service.impl;

import com.lagou.dao.roleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private roleMapper mapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = mapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        List<String> list = mapper.findMenuByRoleId(roleId);
        return list;
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        Integer roleId = roleMenuVo.getRoleId();
        mapper.deleteRoleContextMenu(roleId);
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            Date date = new Date();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleId);
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("System");
            role_menu_relation.setUpdatedby("System");
            mapper.roleMenuRelation(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        mapper.deleteRoleContextMenu(id);
        mapper.deleteRole(id);
    }

    @Override
    public List<ResourceCategory> findRoleResourceCategoryById(Integer roleId) {
        List<ResourceCategory> roleAllResourceCategory = mapper.findRoleAllResourceCategory(roleId);
        return roleAllResourceCategory;
    }

    @Override
    public void RoleContextResourceCategory(RoleResourceVo roleResourceVo) {
        Integer roleId = roleResourceVo.getRoleId();
        mapper.deleteRoleResource(roleId);
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();
        for (Integer rid : resourceIdList) {
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            Date date = new Date();
            roleResourceRelation.setRoleId(roleId);
            roleResourceRelation.setResourceId(rid);
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedBy("System");
            roleResourceRelation.setUpdatedby("System");
            mapper.roleResourceRelation(roleResourceRelation);
        }
    }

    @Override
    public void saveRole(Role role) {
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        mapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {

        role.setUpdatedTime(new Date());

        mapper.updateRole(role);
    }
}

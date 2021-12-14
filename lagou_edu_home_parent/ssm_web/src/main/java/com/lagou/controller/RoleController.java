package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true, 200, "查询角色成功", allRole);
        return result;
    }

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> List = menuService.findSubMenuListByPid(-1);
        ResponseResult result = new ResponseResult(true, 200, "查询角色成功", List);
        return result;
    }

    /**
     * 查询角色关联菜单列表ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {
        List<String> menuList = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", menuList);
        return result;
    }

    /* 用户关联菜单 {roleId: 4, menuIdList: [19, 20, 7, 8, 9, 15, 16, 17, 18]} */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo) {
        roleService.RoleContextMenu(roleMenuVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", "");
        return result;
    }
    /*http://localhost:8080/ssm-web/role/deleteRole?id=5删除角色*/
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", "");
        return result;
    }
    /**/
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        List<ResourceCategory> roleResource = roleService.findRoleResourceCategoryById(roleId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", roleResource);
        return result;
    }
}

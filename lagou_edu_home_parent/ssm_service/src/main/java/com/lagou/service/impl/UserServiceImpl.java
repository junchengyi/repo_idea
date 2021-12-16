package com.lagou.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        /*
        PageHelper.startPage(adVo.getCurrentPage(),adVo.getPageSize());
        List<PromotionAd> allByPage = AdMapper.findAllByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allByPage);
        return pageInfo;*/
// 使用pageHelper
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUser = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<User>(allUser);
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("每页显示长度："+pageInfo.getPageSize());
        System.out.println("是否第一页："+pageInfo.isIsFirstPage());
        System.out.println("是否最后一页："+pageInfo.isIsLastPage());
        return pageInfo;
    }
    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id, status);
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        /*user:发送过来的密码
        * user1:从数据库找出来的密码*/
        if (user1!=null && Md5.verify(user.getPassword(), "lagou", user1.getPassword())){
            return user1;
        }else {
            return null;
        }

    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());
        for (Integer roleid : userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleid);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        List<Role> roles = userMapper.findUserRelationRoleById(id);
        /*保存角色id*/
        List<Integer> ids = new ArrayList<>();
        for (Role role : roles) {
            ids.add(role.getId());
        }
        /**/
        List<Menu> pmenuList = userMapper.findParentMenuByRoleId(ids);

        for (Menu menu : pmenuList) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        List<Resource> resource = userMapper.findResourceByRoleId(ids);

        //6.封装数据
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",pmenuList); //menuList: 菜单权限数据
        map.put("resourceList",resource);//resourceList: 资源权限数据
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }
}

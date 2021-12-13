package com.lagou.controller;


import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        return new ResponseResult(true,200,"查询所有菜单成功",allMenu);
    }

    /*查询父子菜单信息，用在编辑菜单和添加菜单中，若是添加则不需要回显menu信息，若是编辑则需要回显菜单信息*/
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam int id){
        HashMap<String, Object> map = new HashMap<>();
        List<Menu> list = menuService.findSubMenuListByPid(id);
        if (id==-1){
          map.put("menuInfo",null);
          map.put("parentMenuList",list);
          return new ResponseResult(true,200,"查询所有菜单成功",map);
      }else {
          Menu menu = menuService.findMenuById(id);
          map.put("menuInfo",menu);
          map.put("parentMenuList",list);
          return new ResponseResult(true,200,"查询所有菜单成功",map);
      }
    }
}

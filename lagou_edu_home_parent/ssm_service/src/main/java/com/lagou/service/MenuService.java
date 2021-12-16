package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;

import java.util.List;

public interface MenuService {
    /*查询所有父子菜单信息*/
    public List<Menu> findSubMenuListByPid(int pid);
    /*查询菜单列表*/
    public PageInfo<Menu> findAllMenu(MenuVo menuVo);

    public Menu findMenuById(int id);

    void updateMenu(Menu menu);

    void saveMenu(Menu menu);
}

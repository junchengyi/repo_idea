package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        System.out.println(pageInfo.getList());
        return new ResponseResult(true, 200, "查询成功", pageInfo);
    }

    /**
     * 修改用户状态
     * ENABLE能登录，DISABLE不能登录
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status) {
        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id, status);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", status);
        return responseResult;
    }

    /*用户登录
     * */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);
        HashMap<String, Object> map = new HashMap<>();
        if (login != null) {
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id", login.getId());
            HttpSession session = request.getSession();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", login.getId());
            return new ResponseResult(true, 200, "登录成功", map);
        } else {
            return new ResponseResult(true, 200, "登录失败", null);
        }
    }

    /*http://localhost:8080/ssm-web/user/findUserRoleById*/
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id) {
        List<Role> list = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "分配角色成功", list);
    }


    /* 分配角色 */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "分配角色成功", null);
    }


    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的 token
        String token = request.getHeader("Authorization");
        System.out.println(token);
        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String)session.getAttribute("access_token");
        System.out.println(access_token);
        //判断
        if(token.equals(access_token)){
            int user_id = (Integer)session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else{
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;
        }
    }



}

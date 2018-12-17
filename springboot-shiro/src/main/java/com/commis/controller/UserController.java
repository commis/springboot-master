package com.commis.controller;

import com.commis.dao.entity.UserBean;
import com.commis.service.user.UserService;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    @RequiresPermissions("userInfo:view")
    public List<UserBean> list() {
        try {
            return userService.list();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/add")
    @RequiresPermissions("userInfo:add")
    public String userInfoAdd(){
        return "userInfoAdd";
    }

    @DeleteMapping("/del")
    @RequiresPermissions("userInfo:del")
    public String userDel(){
        return "userInfoDel";
    }
}

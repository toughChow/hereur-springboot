package com.toughchow.springbootweb.sys.mybatisplus.controller;


import com.toughchow.springbootweb.sys.mybatisplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author toughchow
 * @since 2019-05-17
 */
@RestController(value = "test_user_controller")
@RequestMapping("/test/user")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Object test() {
        return userService.login();
    }

    @RequestMapping("/reg")
    public Object reg() {
        return userService.reg();
    }

    @RequestMapping("/find")
    public Object find() {
        return userService.find();
    }

    @RequestMapping("/update")
    public Object update() {
        return userService.updateUser();
    }

    @RequestMapping("/page")
    public Object page() {
        return userService.page();
    }



}


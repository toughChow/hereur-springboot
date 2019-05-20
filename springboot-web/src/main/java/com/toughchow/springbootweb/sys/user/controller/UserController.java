package com.toughchow.springbootweb.sys.user.controller;

import com.toughchow.springbootweb.sys.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/sys/user/")
//@CacheConfig(cacheNames = "entityCache")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseBody
//    @Cacheable(key = "#root.target + '_' + #p0 + '_' + #p1")
    public Object testCache(@RequestBody Map map) {
        return userService.login(map);
    }
}

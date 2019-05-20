package com.toughchow.springbootweb.sys.user.service.impl;

import com.toughchow.springbootweb.sys.user.dao.UserDao;
import com.toughchow.springbootweb.sys.user.entity.UserPO;
import com.toughchow.springbootweb.sys.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Cacheable(cacheNames = "user")
    public Object login(Map map) {
        String username = (String) map.get("username");
        UserPO userPO = userDao.findByUsername(username);
        return "成功";
    }
}

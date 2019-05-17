package com.toughchow.springbootweb.sys.test.service.impl;

import com.toughchow.springbootweb.sys.test.entity.UserEntity;
import com.toughchow.springbootweb.sys.test.mapper.UserMapper;
import com.toughchow.springbootweb.sys.test.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author toughchow
 * @since 2019-05-17
 */
@Service(value = "test_userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Object login() {
        Map map = new HashMap<>();
        map.put("username", "tough");
        return userMapper.selectByMap(map);
    }

    @Override
    public Object reg() {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("6");
        userEntity.setUsername("sarah");
        return userMapper.insert(userEntity);
    }

    @Override
    public Object find() {
        String name = "tough";
        return userMapper.findByName(name);
    }
}

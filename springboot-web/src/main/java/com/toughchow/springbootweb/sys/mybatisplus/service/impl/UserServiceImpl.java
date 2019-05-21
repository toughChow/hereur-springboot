package com.toughchow.springbootweb.sys.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toughchow.springbootcommon.mybatis.entity.UserEntity;
import com.toughchow.springbootcommon.mybatis.mapper.UserMapper;
import com.toughchow.springbootweb.sys.mybatisplus.service.IUserService;
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

    @Override
    public Object page() {
        Page<UserEntity> page = new Page<>(0,1);
        IPage<UserEntity> userEntityIPage = userMapper.selectPageVo(page, 1);
        return userEntityIPage;
    }

    /**
     * QueryWrapper UpdateWrapper
     * @return
     */
    @Override
    public Object updateUser() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper();
        wrapper.eq("id",1);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        userEntity.setPassword("666666");
        return userMapper.updateById(userEntity);
    }
}

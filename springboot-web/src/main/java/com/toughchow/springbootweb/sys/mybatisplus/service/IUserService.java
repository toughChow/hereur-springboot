package com.toughchow.springbootweb.sys.mybatisplus.service;

import com.toughchow.springbootcommon.mybatis.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author toughchow
 * @since 2019-05-17
 */
public interface IUserService extends IService<UserEntity> {

    Object login();

    Object reg();

    Object find();

    Object page();

    Object updateUser();
}

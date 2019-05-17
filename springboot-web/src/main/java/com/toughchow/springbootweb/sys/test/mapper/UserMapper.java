package com.toughchow.springbootweb.sys.test.mapper;

import com.toughchow.springbootweb.sys.test.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author toughchow
 * @since 2019-05-17
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    Object findByName(String name);
}

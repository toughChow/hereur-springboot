package com.toughchow.springbootcommon.mybatis.mapper;

import com.toughchow.springbootcommon.mybatis.entity.ProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author toughchow
 * @since 2019-05-21
 */
public interface ProductMapper extends BaseMapper<ProductEntity> {

    int updateQuanrityByPid(Map map);

    @Select("SELECT * FROM t_product")
    List<ProductEntity> selectTimerTask();
}

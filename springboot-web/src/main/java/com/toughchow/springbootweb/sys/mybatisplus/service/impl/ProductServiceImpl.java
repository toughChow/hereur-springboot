package com.toughchow.springbootweb.sys.mybatisplus.service.impl;

import com.toughchow.springbootcommon.mybatis.entity.ProductEntity;
import com.toughchow.springbootcommon.mybatis.mapper.ProductMapper;
import com.toughchow.springbootweb.sys.mybatisplus.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author toughchow
 * @since 2019-05-21
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements IProductService {

}

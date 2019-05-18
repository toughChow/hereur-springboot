package com.toughchow.springbootcommon.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充功能，字段上必须添加@TableField(fill = FieldFill.UPDATE)
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("metaObjectHandler start insert fill...");
        this.setFieldValByName("createTime",new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("metaObjectHandler start update fill...");
        this.setFieldValByName("updateTime",new Date(), metaObject);
    }
}

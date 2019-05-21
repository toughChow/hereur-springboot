package com.toughchow.springbootcommon.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author toughchow
 * @since 2019-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
public class UserEntity implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(type = IdType.UUID)
    private String id;

    private String password;

    private String username;

    private Integer state;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

}

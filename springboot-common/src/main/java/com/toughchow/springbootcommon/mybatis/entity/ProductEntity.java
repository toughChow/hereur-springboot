package com.toughchow.springbootcommon.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author toughchow
 * @since 2019-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product")
public class ProductEntity implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(type = IdType.UUID)
    private String id;

    private String name;

    private Float lowPrice;

    private Float newPrice;

    private Integer quantity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}

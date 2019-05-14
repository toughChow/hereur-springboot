package com.toughchow.springbootweb.sys.user.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_sys_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class UserPO {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(length = 64)
    private String username;

    @Column(length = 64)
    private String password;

}

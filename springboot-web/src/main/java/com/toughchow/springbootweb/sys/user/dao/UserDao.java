package com.toughchow.springbootweb.sys.user.dao;

import com.toughchow.springbootweb.sys.user.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserPO, String> {
    UserPO findByUsername(String username);
}

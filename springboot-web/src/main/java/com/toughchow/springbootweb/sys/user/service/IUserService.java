package com.toughchow.springbootweb.sys.user.service;

import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public interface IUserService {
    @Cacheable(value = "entityCache", key = "#map")
    Object login(Map map);
}

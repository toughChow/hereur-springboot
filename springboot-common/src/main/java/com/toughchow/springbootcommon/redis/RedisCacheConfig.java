package com.toughchow.springbootcommon.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author: create by ToughChow
 * @version: v1.0
 * @description: com.toughchow.springbootcommon.redis
 * @date:2019/5/20
 */
@Slf4j
@Configuration(value = "customRedisCacheConfig")
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager redisCacheConfig(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(60)) //60s缓存失效
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(kyroSerializer())) // 设置key的序列化方式
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(kyroSerializer()))// 设置value的序列化方式
            .disableCachingNullValues();

        // 获取redis缓存管理类
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(configuration)
                .transactionAware()
                .build();

        log.info("Custom RedisCacheManager 加载完成");
        return redisCacheManager;
    }

    // 自定义key生成器
    @Bean
    public KeyGenerator keyGenerator(){
        return (o, method, params) ->{
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName()); // 类目
            sb.append(method.getName()); // 方法名
            for(Object param: params){
                sb.append(param.toString()); // 参数名
            }
            return sb.toString();
        };
    }

    // key键序列化方式
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }
    // value值序列化方式
    private GenericJackson2JsonRedisSerializer valueSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    // 使用kyro序列化
    private RedisSerializer<String> kyroSerializer() {
        // KryoRedisSerializer 替换默认序列化
        return new KryoRedisSerializer(Object.class);
    }
}

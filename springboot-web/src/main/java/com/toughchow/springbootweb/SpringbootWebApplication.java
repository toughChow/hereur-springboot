package com.toughchow.springbootweb;

import com.toughchow.springbootcommon.netty.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableCaching
@ComponentScans({@ComponentScan(value = "com.toughchow.springbootcommon")})
public class SpringbootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
    }

    @Bean
    CommandLineRunner startNettySocket() {
        return args -> new NettyServer().start();
    }
}

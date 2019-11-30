package cn.ct.ks;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// 暂时用不到jdbc和redis， 排除自动配置
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class})
@EnableDubbo
public class SsoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoTestApplication.class, args);
    }
}

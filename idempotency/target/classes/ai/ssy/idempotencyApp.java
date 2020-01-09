package ai.ssy;

import ai.ssy.interceptor.ApiIdempotentInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: idempotency
 * @description: ss
 * @author: ssy
 * @create: 2020-01-09 09:22
 **/
@SpringBootApplication
@MapperScan("ai.ssy.mapper")
public class idempotencyApp  extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(idempotencyApp.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 接口幂等性拦截器
        registry.addInterceptor(apiIdempotentInterceptor());

        super.addInterceptors(registry);
    }

    @Bean
    public ApiIdempotentInterceptor apiIdempotentInterceptor() {
        return new ApiIdempotentInterceptor();
    }


}
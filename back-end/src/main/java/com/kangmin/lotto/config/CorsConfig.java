package com.kangmin.lotto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9090")
                .allowedMethods("GET", "POST"); // AWS에서 기본 세팅이 GET만 허용되어 있음, 그 이외의 메서드를 추가하려면 여기서 추가해야함
    }
}

package com.coding404.myweb.util.config;

import com.coding404.myweb.util.interceptor.UserAuthHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //스프링 설정파일로 사용
public class WebConfig implements WebMvcConfigurer {

    //스프링에 빈으로 등록
    @Bean
    public UserAuthHandler userAuthHandler() {
        return new UserAuthHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( userAuthHandler() ) //실행시킬 인터셉터 클래스 등록
                //.addPathPatterns("/user/**"); //모든 경로
                .addPathPatterns("/user/**") //user로 시작하는 경로
                .addPathPatterns("/product/**") //product로 시작하는 경로
                .excludePathPatterns("/user/login") //이 경로는 제외
                .excludePathPatterns("/user/join")
                .excludePathPatterns("/user/logout");

        //여러 인터셉터를 다른 경로별로 동작시키려면 하나 더 만들면 됨
        //registry.addInterceptor(userAuthHandler())
    }
}

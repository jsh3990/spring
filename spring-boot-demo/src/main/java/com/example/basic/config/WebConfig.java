package com.example.basic.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//MVC 모듈에서 필요한 IOC설정을 하려면 WebMvcConfigure 인터페이스를 상속을 받음
@Configuration
@PropertySource("classpath:myapp.properties") //application.properties파일 이외에 다른 파일을 설정파일로 쓰고 싶을 때
public class WebConfig implements WebMvcConfigurer {

//    //properties파일의 값을 읽어서 자바코드로 가져올 때는
//    @Value("${myapp.name}")
//    private String myApp;
//
//    //app.properties에 선언된 값
//    @Value("${server.port}")
//    private String port;
//
//    @PostConstruct //생성된 이후에 실행할 메서드
//    public void example() {
//        System.out.println("서버를 가동할 때 최초에 실행됨: " + myApp);
//        System.out.println("서버를 가동할 때 최초에 실행됨: " + port);
//    }
//
//    //스프링에 빈을 직접 등록하거나, 특정 기능을 커스터마이징 할 때 사용할 수 있음
}

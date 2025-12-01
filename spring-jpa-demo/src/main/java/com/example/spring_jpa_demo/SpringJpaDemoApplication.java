package com.example.spring_jpa_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //자동 생성되는 날짜를 쓰고 싶으면 선언
public class SpringJpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaDemoApplication.class, args);
    }

}

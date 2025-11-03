package com.example.basic.chap04.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링에 필요한 객체들을 생성하는 설정파일로 사용을 하겠음
public class AppConfig {

    @Bean //스프링이 메서드를 실행시켜서 IOC 컨테이너에 등록
    public Chef chef() {
        return new Chef("IOC.김");
    }

    @Bean Hotel hotel() {
        //return new Hotel( new Chef("~~")); //직접 생성
        return new Hotel( chef() );
    }
}

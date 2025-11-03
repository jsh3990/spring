package com.example.basic.chap04.di;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootTest
public class MainClass {

    @Test //이 메서드만 main메서드처럼 동작을 시킴
    public void test01() {
        //자바 객체로 직접 생성을 한다면
        Chef chef = new Chef("mr.홍");
        Hotel hotel = new Hotel(chef); //생성자 주입

        Chef chef2 = new Chef("mr.킴");
        hotel.setChef(chef2); //setter 주입
    }
    //
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    public void test02() {
        //IOC 컨테이너에 미리 생성된 객체 확인
//        String[] arr = applicationContext.getBeanDefinitionNames();
//        System.out.println(Arrays.toString(arr));

        //스프링에 미리 생성된 Chef 객체를 얻기
//        Chef chef = applicationContext.getBean(Chef.class);
//        System.out. println(chef.cook("파스타"));

        //스프링에 미리 생성된 Hotel 객체를 얻기
        Hotel hotel = applicationContext.getBean(Hotel.class);
        Chef c = hotel.getChef();
        System.out.println(c.cook("라면"));
    }
}

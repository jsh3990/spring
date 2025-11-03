package com.example.basic.chap04.autowired;

import com.example.basic.chap04.di.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootTest
public class MainClass {

    //IOC
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

    @Test
    public void test01() {

        applicationContext.scan("com.example.basic.chap04.autowired"); //해당 패키지 읽으라는 뜻
        applicationContext.refresh();


        String[] arr = applicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.toString(arr)); //printer, document가 있는지
    }

    //Autowired 확인하기
    @Autowired
    ApplicationContext context;

    @Autowired
    Printer printer;

    @Test
    public void test02() {

        Document doc = printer.getDocument();
        System.out.println(doc.data);

    }
}

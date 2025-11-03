package com.example.basic.chap04.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainClass {

    private IBattery battery;

    public MainClass(@Qualifier("x") IBattery battery) {
        this.battery = battery;
    }

//    @Autowired
//    @Qualifier("y") //연결할 빈 이름
//    private IBattery battery;
    /*
	TODO
	1. IBattery를 구현체 Battery01 클래스를 만듭니다.
	2. MainClass에서 멤버변수 주입 시키고 Test메서드에서 확인하세요.
	
	*/

    @Test
    public void test() {
        System.out.println(battery.getInfo());
    }

}

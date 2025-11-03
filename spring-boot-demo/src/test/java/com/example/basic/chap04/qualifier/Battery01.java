package com.example.basic.chap04.qualifier;

import org.springframework.stereotype.Component;

@Component("x") //이 빈의 이름을 명시 x
public class Battery01 implements IBattery{

    @Override
    public String getInfo() {
        return "에너자이저";
    }

}

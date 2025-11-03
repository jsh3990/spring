package com.example.basic.chap03;

import java.time.LocalDateTime;

public class SimpleVO {

    private String name;
    private int age;
    private LocalDateTime regdate;

    public SimpleVO() {}
    public SimpleVO(String name, int age, LocalDateTime regdate) {
        this.name = name;
        this.age = age;
        this.regdate = regdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getRegdate() {
        return regdate;
    }

    public void setRegdate(LocalDateTime regdate) {
        this.regdate = regdate;
    }
}

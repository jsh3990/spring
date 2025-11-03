package com.example.basic.chap04.di;

public class Chef {

    private String name;
    public Chef(String name) {
        this.name = name;
    }

    public String cook(String menu) {
        return name + "cooking is " + menu;
    }
}

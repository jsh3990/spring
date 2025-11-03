package com.example.basic.chap04.di;

public class Hotel {
    //호텔클래스가 동작하는데 chef가 필요한 상황 -> 호텔이 쉐프에 의존적임
    private Chef chef;

    public Hotel(Chef chef) {
        this.chef = chef;
    }

    //setter, getter
    //세터 메서드를 통해서 chef 주입
    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }
}

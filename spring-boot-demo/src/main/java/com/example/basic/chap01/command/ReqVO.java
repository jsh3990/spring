package com.example.basic.chap01.command;

//VO(Value Object) = 값을 저장하는 용도의 클래스

import java.util.ArrayList;

public class ReqVO {
    //1. 멤버변수는 은닉, getter, setter를 생성함
    private String id;
    private String pw;
    private String name;
    private ArrayList<String> inter;


    //alt+ insert -> 컨스트럭터
    //2. 생성자는 기본생성자, 멤버변수 개수만한 생성자
    public ReqVO() {}
    public ReqVO(String id, String pw, String name, ArrayList<String> inter) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.inter = inter;
    }

    @Override
    public String  toString() {
        return "ReqVO{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", inter=" + inter +
                '}';
    }

    //3. getter, setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getInter() {
        return inter;
    }

    public void setInter(ArrayList<String> inter) {
        this.inter = inter;
    }
}

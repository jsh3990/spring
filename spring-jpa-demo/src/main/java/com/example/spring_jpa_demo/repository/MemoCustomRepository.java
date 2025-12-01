package com.example.spring_jpa_demo.repository;

import com.example.spring_jpa_demo.entity.Memo;

import java.util.List;

public interface MemoCustomRepository {
    //구현체가 가져야할 추상메서드 선언
    int updateTest(String writer, String text);
    //JPQL조인
    List<Memo> mtoJoin1();
}

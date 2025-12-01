package com.example.spring_jpa_demo.repository;

import com.example.spring_jpa_demo.command.MemberMemoDTO;
import com.example.spring_jpa_demo.entity.Member;
import com.example.spring_jpa_demo.entity.Memo;

import java.util.List;

public interface MemoCustomRepository {
    //구현체가 가져야할 추상메서드 선언
    int updateTest(String writer, String text);
    //JPQL조인
    List<Memo> mtoJoin1();
    List<Object[]> mtoJoin2(long id); //두개 이상의 엔티티 받기
    List<Object[]> mtoJoin3(String writer); //연관관계가 없는 조인
    List<Memo> mtoJoin4(); //Fetch조인

    Member oTmJoin1(String id); //원투매니
    List<Member> oTmJoin2(String id); //원투매니 fetch
    MemberMemoDTO oTmJoin3(String id); //조인결과를 DTO로 받기

    //쿼리DSL
    Memo dslSelect(); //기본구문 단일행 조회
    List<Memo> dslSelect2(); //여러행 조회
    List<Memo> dslSelect3(String searchType, String searchName); //불린빌더 동적쿼리
}

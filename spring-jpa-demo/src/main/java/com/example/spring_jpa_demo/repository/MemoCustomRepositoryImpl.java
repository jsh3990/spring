package com.example.spring_jpa_demo.repository;

import com.example.spring_jpa_demo.entity.Memo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MemoCustomRepositoryImpl implements MemoCustomRepository {

    //엔티티 매니저 - 영속성 영역에 접근 가능한 객체
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public int updateTest(String writer, String text) {
        //자바 관련 프로그램 코드 작성
        String sql = "update Memo m set m.writer = :a where m.writer = :b"; //JPQL

        Query query = entityManager.createQuery(sql); //sql구문 준비
        query.setParameter("a", text); //a 변수에 text값 세팅
        query.setParameter("b", writer); //b 변수에 writer값 세팅
        //insert, update, delete는 executeUpdate문을 사용함
        //select getResultList, getSingleResult를 사용함
        int result = query.executeUpdate();
        return result;

    }

    @Override
    public List<Memo> mtoJoin1() {
        String sql = "select m from Memo m left join m.member";
        //실행결과는 Query 또는 TypeQuery로 받을 수 있음
        TypedQuery<Memo> query = entityManager.createQuery(sql, Memo.class); //반환타입
        List<Memo> list = query.getResultList(); //한행이라면 getSingleResult로 받음
        return list;
    }
}

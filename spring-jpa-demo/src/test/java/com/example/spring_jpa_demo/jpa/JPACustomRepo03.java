package com.example.spring_jpa_demo.jpa;

import com.example.spring_jpa_demo.entity.Memo;
import com.example.spring_jpa_demo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPACustomRepo03 {

    @Autowired
    MemoRepository memoRepository;

//    @Test
//    public void testCode01() {
//        int result = memoRepository.updateTest("admin9", "업데이트할값");
//        System.out.println("업데이트 성공여부: " + result);
//    }

    //조인구문 실행
    @Test
    public void testCode02() {
        List<Memo> list = memoRepository.mtoJoin1();
        System.out.println(list.toString());
    }
}

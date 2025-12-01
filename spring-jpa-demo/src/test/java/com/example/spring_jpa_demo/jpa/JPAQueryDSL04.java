package com.example.spring_jpa_demo.jpa;

import com.example.spring_jpa_demo.entity.Memo;
import com.example.spring_jpa_demo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPAQueryDSL04 {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testCode01() {
        Memo memo = memoRepository.dslSelect();
        System.out.println(memo.toString());
    }

    @Test
    public void testCode02() {
        List<Memo> list = memoRepository.dslSelect2();
        System.out.println( list.toString() );
    }

    @Test
    public void testCode03() {
        String searchType = "text";
        String searchName = "3";

        List<Memo> list = memoRepository.dslSelect3(searchType, searchName);
        System.out.println( list.toString() );
    }



}

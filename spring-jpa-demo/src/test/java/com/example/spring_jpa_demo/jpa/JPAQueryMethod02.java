package com.example.spring_jpa_demo.jpa;

import com.example.spring_jpa_demo.entity.Memo;
import com.example.spring_jpa_demo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class JPAQueryMethod02 {
    @Autowired
    MemoRepository memoRepository;

//    @Test
//    public void testCode01() {
//        //List<Memo> list = memoRepository.findByIdBetween(10L, 20L);
//        //List<Memo> list = memoRepository.findByTextLike("%1%");
//        //List<Memo> list = memoRepository.findByTextLikeOrderByIdDesc("%1%");
//        //List<Memo> list = memoRepository.findByWriterIn( Arrays.asList("10", "20", "30"));
////        Pageable pageable = PageRequest.of(0, 10);
////        Page<Memo> list = memoRepository.findByTextLikeOrWriterLike("%12%", "%13%", pageable);
////        System.out.println(list.getContent().toString());
//    }

    //JPQL
//    @Test
//    public void testCode02() {
//        //List<Memo> list = memoRepository.getListDesc();
//        //List<Memo> list = memoRepository.getListLike("10");
//        List<Object[]> list = memoRepository.getListAsc();
//        System.out.println(list.toString());
//    }

//    @Test
//    public void testCode03() {
//        Memo memo = Memo.builder().id(5L).writer("업데이트").text("업데이트").build();
//        int result = memoRepository.updateMemo(memo);
//        System.out.println("업데이트 성공여부: " + result);
//    }

//    @Test
//    public void testCode04() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Memo> list = memoRepository.getListJpa(5L, pageable);
//        System.out.println(list.getContent().toString());
//    }

    @Test
    public void testCode05() {
        List<Memo> list = memoRepository.getNativeQuery(5L);
        System.out.println(list.toString());
    }
}

package com.example.spring_jpa_demo.jpa;


import com.example.spring_jpa_demo.entity.Memo;
import com.example.spring_jpa_demo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class JPABasic01 {
    @Autowired
    MemoRepository memoRepository;


//    @Test
//    public void testCode01() {
//        for (int i = 1 ; i <= 30; i++) {
//            Memo memo = Memo.builder().writer("admin" + i).text("admin" + i).build();
//            memoRepository.save(memo); //insert
//        }
//
//    }

//    @Test
//    public void testCode02() {
//        Optional<Memo> memo = memoRepository.findById(10L);
//        if(memo.isPresent()) {//값이 있다면 true
//            Memo result = memo.get();
//            System.out.println(result.toString());
//        }
//    }

//    @Test
//    public void testCode03() {
//        List<Memo> list = memoRepository.findAll();
//        System.out.println(list.toString());
//    }

//    @Test
//    public void testCode04() {
//        //20번을 업데이트 - 내부적으로 select 수행 후에 값이 있다면 update를 진행
//        Memo memo = Memo.builder().id(20L).writer("업데이트").text("업데이트").build();
//        Memo result = memoRepository.save(memo);
//        System.out.println("업데이트 된 결과: " + result.toString()); //업데이트 된 결과
//    }

//    @Test
//    public void testCode05() {
//        memoRepository.deleteById(21L); //21번 삭제
//    }

    //페이지 객체를 사용해서 select
//    @Test
//    public void testCode06() {
//        //정렬
//        Sort sort = Sort.by("id").descending();
//        Sort sort2 = Sort.by("text").ascending();
//        sort = sort.and(sort2);
//
//        Pageable pageable = PageRequest.of(0, 10, sort);
//        Page<Memo> page = memoRepository.findAll(pageable); //페이지 처리를 한 결과
//
//        System.out.println("총 페이지수: " + page.getTotalPages() );
//        System.out.println("총 데이터수: " + page.getTotalElements() );
//        System.out.println("현재 페이지 번호: " + page.getNumber() );
//        System.out.println("데이터 존재여부: " + page.hasContent() );
//        System.out.println("시작페이지 여부: " + page.isFirst() );
//        System.out.println("마지막페이지 여부: " + page.isLast() );
//        System.out.println("데이터 개수: " + page.getSize());
//        System.out.println("데이터: " + page.getContent().toString() );
//
//    }
}

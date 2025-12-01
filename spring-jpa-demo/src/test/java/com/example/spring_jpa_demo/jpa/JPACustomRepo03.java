package com.example.spring_jpa_demo.jpa;

import com.example.spring_jpa_demo.command.MemberMemoDTO;
import com.example.spring_jpa_demo.entity.Member;
import com.example.spring_jpa_demo.entity.Memo;
import com.example.spring_jpa_demo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
    @Transactional //Lazy로딩시에 추가
    public void testCode02() {
        List<Memo> list = memoRepository.mtoJoin1();
        System.out.println(list.toString());
    }

    //    @Test
//    public void testCode03() {
//        List<Object[]> list = memoRepository.mtoJoin2(1L);
//        for(Object[] arr : list ) {
//            System.out.println(  Arrays.toString(arr)  );
//        }
//    }

    @Test
    public void testCode04() {
        List<Object[]> list = memoRepository.mtoJoin3("admin5");
        for(Object[] arr : list ) {
            System.out.println(  Arrays.toString(arr)  );
        }
    }

//    @Test
//    public void testCode05() {
//        List<Memo> list = memoRepository.mtoJoin4();
//        for(Memo m :list ) {
//            System.out.println(m.toString());
//        }
//    }


    //원투매니
    @Test
    @Transactional
    public void testCode06() {
        Member member = memoRepository.oTmJoin1("abc");
        System.out.println(member.toString());
    }

    @Test
    public void testCode07() {
        List<Member> list = memoRepository.oTmJoin2("abc");
        for(Member m : list ) {
            System.out.println(m.toString());
        }
    }

    //DTO로 결과받기
    @Test
    public void testCode08() {
        MemberMemoDTO dto = memoRepository.oTmJoin3("def");
        System.out.println(dto.toString());
    }
}

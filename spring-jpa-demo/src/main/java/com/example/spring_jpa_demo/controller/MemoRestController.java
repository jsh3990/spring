package com.example.spring_jpa_demo.controller;

import com.example.spring_jpa_demo.entity.Memo;
import com.example.spring_jpa_demo.entity.QMemo;
import com.example.spring_jpa_demo.repository.MemoRepository;
import com.example.spring_jpa_demo.util.Criteria;
import com.example.spring_jpa_demo.util.PageVO;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemoRestController {
    //서비스 영역 생략

    @Autowired
    private MemoRepository memoRepository;

    //JSON 형변환 과정에서 양방향 참조가 발생함
    //1. 반환을 DTO로 만들어서 처리
    //2. @JsonIgnore
    @GetMapping("/list")
    public PageVO<Memo> getList(Criteria cri) { //(페이지번호, 데이터 개수) 받음 + 검색에 키워드 받음

        System.out.println(cri.toString());

        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(cri.getPage()-1, cri.getAmount(), sort);

        //동적쿼리구무을 작성 - 쿼리 DSL의 불린빌더
        QMemo memo = QMemo.memo;
        //조건을 조합할 수 있는 빌더 객체
        BooleanBuilder builder = new BooleanBuilder();

        if( !cri.getSearchName().isEmpty() && cri.getSearchType().equals("mno") ) {
            builder.and(memo.id.like("%" + cri.getSearchName() + "%") );
        }
        if( !cri.getSearchName().isEmpty() && cri.getSearchType().equals("writer") ) {
            builder.and(memo.writer.like("%" + cri.getSearchName() + "%") );
        }
        if( !cri.getSearchName().isEmpty() && cri.getSearchType().equals("text") ) {
            builder.and(memo.text.like("%" + cri.getSearchName() + "%") );
        }
        if( !cri.getSearchName().isEmpty() && cri.getSearchType().equals("textWriter") ) {
            builder.and(memo.text.like("%" + cri.getSearchName() + "%") )
                    .or(memo.writer.like("%" + cri.getSearchName() + "%") );
        }


        Page<Memo> page = memoRepository.findAll(builder, pageable); //불린빌더, 페이지객체
        PageVO pageVO = new PageVO(page);
        return pageVO;
    }
}

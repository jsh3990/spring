package com.coding404.myweb.util;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data //getter, setter tostring
public class PageVO {
    //페이지네이션을 그리기 위한 클래스
    private int start; //pageNation 시작번호
    private int end; //pn 끝번호
    private boolean prev; //이전버튼 활성화 여부
    private boolean next; //다음버튼 활성화 여부

    private int page; //현재 조회하고 있는 페이지 번호
    private int amount; //현재 조회하고 있는 데이터 개수
    private int total; //전체 게시글 수

    private int realEnd; //실제 끝번호

    private List<Integer> pageList; //타임리프에서는 향상된 for구문밖에 없어서 start~end까지 리스트에 저장

    //생성자
    public PageVO(Criteria cri, int total) {
        this.page = cri.getPage();
        this.amount = cri.getAmount();
        this.total = total;

        //1. 페이지 끝번호 계산 - 올림(조회하는 페이지 번호 / 페이지네이션 수) * 페이지네이션 수
        //5번페이지 조회 -> 끝페이지네이션 10
        //15번페이지 조회 -> 끝페이지네이션 20
        this.end = (int)Math.ceil( page / 10.0 ) * 10;

        //2. 시작번호
        //this.start = 끝페이지번호 - 페이지네이션 수 + 1;
        this.start = end - 10 + 1;

        //3. 진짜 끝번호를 재계산
        //만약에 게시글 개수가 53개 -> 마지막 끝 페이지 번호 6
        //만약 게시글 개수가 174개 -> 마지막 끝 페이지 번호 18
        //올림 (전체게시글수 / 데이터 개수)
        this.realEnd = (int)Math.ceil( this.total / (double)amount );

        //4. 맨마지막 페이지네이션일 경우는 realEnd로 변경
        //304개 게시물 end -> 10, 20, 30, 40, 50
        //realEnd -> 31
        if(this.end > this.realEnd) {
            this.end = this.realEnd;
        }
        //5. 이전버튼 활성화여부
        //start -> 1, 11, 21, 31......
        this.prev = this.start > 1;

        //6. 다음버튼 활성화여부
        this.next = this.realEnd > this.end;

        //7. 페이지리스트 초기화
        this.pageList = IntStream.rangeClosed(start, end).boxed().toList();
    }
}

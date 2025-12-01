package com.example.spring_jpa_demo.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberMemoDTO {
    //조인결과를 저장할 DTO클래스 
    //멤버필드
    private String id;
    private String name;
    private LocalDateTime signDate;
    //메모필드
    private String writer;
    private String text;

}

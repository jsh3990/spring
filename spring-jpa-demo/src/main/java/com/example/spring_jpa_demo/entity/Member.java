package com.example.spring_jpa_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Entity //JPA가 이 클래스를 entity 로 관리함
@Table(name="MEMBER") //테이블명
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {

    @Id //pk지정
    private String id;
    @Column(nullable = false, length = 50) //
    private String name;
    @CreatedDate //JPA가 인서트시 날짜를 자동 입력 (오디팅 설정 필요)
    @Column(nullable = false)
    private LocalDateTime signDate;
}

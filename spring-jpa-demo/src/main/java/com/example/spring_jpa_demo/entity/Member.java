package com.example.spring_jpa_demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity //JPA가 이 클래스를 entity 로 관리함
@Table(name="MEMBER") //테이블명
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {
    //관계 1
    @Id //pk지정
    private String id;
    @Column(nullable = false, length = 50) //
    private String name;
    @CreatedDate //JPA가 인서트시 날짜를 자동 입력 (오디팅 설정 필요)
    @Column(nullable = false)
    private LocalDateTime signDate;

    //원투매니 단방향
//    @OneToMany(fetch = FetchType.EAGER) //원투매니 로딩전략기본 - Lazy
//    @JoinColumn(name = "member_id") //Memo테이블의 member_id를 FK로 사용하겠음 - 적지 않으면 관계의 주인이 member가 되면서 맵핑테이블을 생성해 버리게 됨
//    private List<Memo> list = new ArrayList<>();

    //양방향 맵핑
    //toString은 한쪽에만 사용함
    @JsonIgnore
    @OneToMany(mappedBy = "member") //연관관계의 주인 (FK를 관리할 필드 - Memo에 존재하는 member필드)
    private List<Memo> list = new ArrayList<>();
}

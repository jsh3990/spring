package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoOrderVO {
    private int oId;
    private int mId; //FK
    private String productName;

    //N:1 - 1관계의 멤버를 추가
    //private String name; //가져올 컬럼이 하나인 경우는, 이렇게 추가하는 방법도 고려함
    private DemoMemberVO memberVO;
}

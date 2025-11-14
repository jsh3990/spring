package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoMemberVO {
    private int mId;
    private String name;

    //N쪽의 List를 선언
    List<DemoOrderVO> orderVO;
}

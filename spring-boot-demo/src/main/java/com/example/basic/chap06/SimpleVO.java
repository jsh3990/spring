package com.example.basic.chap06;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleVO {

    private int num;
    private String first;
    private String last;
    private LocalDateTime regdate;
}

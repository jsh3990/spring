package com.example.basic.chap05.jdbc;

import org.apache.ibatis.annotations.Mapper;

@Mapper //마이바티스가 @Mapper를 읽어서 인터페이스로 인식
public interface TestMapper {
    //추상메서드선언
    String getTime();

}

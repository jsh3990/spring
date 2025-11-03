package com.example.basic.chap05;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoMapper {
    void memoRegist(MemoVO memoVO); //메모 등록
    List<MemoVO> list(); //메모 목록
}

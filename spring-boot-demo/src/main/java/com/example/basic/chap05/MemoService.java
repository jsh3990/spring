package com.example.basic.chap05;

import java.util.List;

public interface MemoService {

    List<MemoVO> list();
    void memoRegist(MemoVO memoVO);
}

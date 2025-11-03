package com.example.basic.chap05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memoService")
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public List<MemoVO> list() {
        return memoMapper.list();
    }

    @Override
    public void memoRegist(MemoVO memoVO) {
        memoMapper.memoRegist(memoVO);
    }
}

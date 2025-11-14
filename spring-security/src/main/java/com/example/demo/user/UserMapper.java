package com.example.demo.user;

import com.example.demo.command.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void join(UserVO vo); //회원가입
     UserVO login(String username); //로그인
}

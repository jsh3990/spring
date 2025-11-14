package com.example.demo.security.config;

import com.example.demo.command.UserVO;
import com.example.demo.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    //1. loginProcessURL로 로그인 경로를 등록
    //2. 클라이언트에서 넘어오는 id 역할의 name값은 기본이 username
    // form 태그의 name 값을 변경하고 싶다면 설정파일에 usernameParameter 등록하면 된다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("야 이거 실행됐어: " + username);

        //로그인 시도 코드를 작성
        //id가 없는 아이디면 null반환
        //있는 id는 userVO반환
        UserVO userVO = userMapper.login(username);
        if(userVO == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없음");
        }

        return new MyUserDetails(userVO); //아이디가 있다면, 약속된 형태의 객체를 시큐리티에 반환해야함
        //여기서 반환되는 리턴은 = 시큐리티세션 ( new Authentication( new MyUserDetails() ) ) 형식으로 저장을 시킨다.

    }
}

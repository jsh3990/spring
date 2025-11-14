package com.example.demo.security.config;

import com.example.demo.command.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//이 객체는 화면에서 전달하는 값을 포함하도록 처리하고, 반드시 변수에 대한 getter 생성을 해줘야함
//이 객체를 스프링이 반환으로 받아가서 비밀번호, 권한을 검증할 때 사용함
public class MyUserDetails implements UserDetails {

    //vo객체
    private UserVO userVO;

    //생성자
    public MyUserDetails(UserVO userVO) {
        this.userVO = userVO;
    }

    //사용자의 권한을 리스트 형태로 구현해서 담아서 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userVO.getRole();
            }
        });
        return list; //권한을 리스트 형태로 반환하면, 스프링 시큐리티가 확인해줌
    }

    //여기에다가 필요한 getter 메서드를 더 만들어 줄 수 있다.
    public String getRole() {
        return userVO.getRole();
    }

    @Override
    public String getPassword() {
        return userVO.getPassword();
    }

    @Override
    public String getUsername() {
        return userVO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //계정이 만료되지 않았습니까? (true = 네)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //계정이 잠기지 않았습니까?
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //비밀번호 만료되지 않았습니까?
    }

    @Override
    public boolean isEnabled() {
        return true; //계정 사용할 수 있습니까?
    }
}

package com.example.demo.controller;

import com.example.demo.command.UserVO;
import com.example.demo.security.config.MyUserDetails;
import com.example.demo.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    //시큐리티가 적용되면 모든 요청에 대해서 시큐리티가 제공하는 기본 로그인 화면이 보여지게 된다.
    //기본 아이디는 user, 패스워드는 로그에 있다.
    //로그아웃의 기본 경로는 /logout이다
    //로그아웃 이후에는 /hello페이지로 진입이 불가능
    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        //로그인이 된 사람이면 있음, 로그인이 안 된 사람이면 없음
        if(authentication != null) {
            MyUserDetails myUserDetails= (MyUserDetails) authentication.getPrincipal();
            System.out.println(myUserDetails.getUsername());
            System.out.println(myUserDetails.getPassword());
            System.out.println(myUserDetails.getRole());
        }

        return "hello";
    }

    @GetMapping("/all")
    public String all() {
        return "all";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {

        if(error != null) {
            model.addAttribute("msg", "아이디 비밀번호를 입력하세요");
        }

        return "login";
    }

    //회원가입 기능 - 직접 구현
    @PostMapping("/joinForm")
    public String joinForm(UserVO vo) {

        String password = bCryptPasswordEncoder.encode(vo.getPassword());
        vo.setPassword(password);
        userMapper.join(vo);

        return"redirect:/login";
    }

    //REST방식 - 누구나 전부 사용이 가능함
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/test")
    public @ResponseBody String test() {
        return "REST API test";
    }
}

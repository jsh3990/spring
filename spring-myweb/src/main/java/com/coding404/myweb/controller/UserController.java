package com.coding404.myweb.controller;

import com.coding404.myweb.command.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "user/join";
    }

    @GetMapping("/userDetail")
   public String userDetailPage(HttpSession session) {

//        UserVO userVO = (UserVO)session.getAttribute("userVO");
//        if(userVO == null) { //로그인 안 한 사람
//            return "redirect:/user/login";
//        }

        //세션확인
        return "user/userDetail";
   }

    //로그인요청
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes ra,
                        HttpSession session) {

        //DB에서 회원정보를 확인한 후에 로그인 처리
        //SELECT * FROM 유저테이블 WHERE ID = ? AND PW = ?
        //반환타입은 UserVO 타입
        //UserVO가 null 이면 로그인 실패, userVO가 값이 있다면 로그인 성공
        System.out.println("로그인 정보:" + username + "," + password);

        UserVO userVO = new UserVO("abc123", null); //로그인 시도!!
        if(userVO != null) { //로그인 성공
            //세션에 유저정보를 저장.
            session.setAttribute("userVO", userVO); //유저의 인증정보를 저장

            return "redirect:/main"; //홈화면
        } else { //로그인 실패
            ra.addFlashAttribute("msg", "아이디 비밀번호를 확인하세요");
            return "redirect:/user/login"; //로그인페이지
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {

        //세션에 저장된 userVO 삭제
        //session.removeAttribute("userVO");

        //기존 세션객체를 무효화
        session.invalidate();

        return "redirect:/user/login";
    }
}

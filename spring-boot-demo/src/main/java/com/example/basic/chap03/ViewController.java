package com.example.basic.chap03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chap03")
public class ViewController {

    //ex01
    @GetMapping("/ex01")
    public void ex01() {}

    //ex02
    @GetMapping("/ex02")
    public String ex02(Model model) {

        //1개의 vo = 1회원 정보
        List<SimpleVO> list = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            SimpleVO vo = new SimpleVO();
            vo.setName("홍길동" + i);
            vo.setAge(20 + i);
            vo.setRegdate( LocalDateTime.now());
            list.add(vo);
        }
        model.addAttribute("list", list); //모델에 저장

        return "chap03/ex02";
    }

    //ex03
    @GetMapping("/ex03")
    public String ex03(Model model) {

        List<SimpleVO> list = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            SimpleVO vo = new SimpleVO();
            vo.setName("홍길동" + i);
            vo.setAge(20 + i);
            vo.setRegdate( LocalDateTime.now());
            list.add(vo);
        }
        model.addAttribute("list", list); //모델에 저장

        return "chap03/ex03";
    }

    @GetMapping("/ex03_ok")
    public String ex03_ok(@RequestParam("name") String name) {
        System.out.println("클라이언트에서 전달받은 값: " + name);
        return "chap03/ex03_ok";
    }

    //ex04
    @GetMapping("/ex04")
    public String ex04(Model model) {
        model.addAttribute("name", "홍길동");
        model.addAttribute("arr", new int[] {1,2,3,4,5} );

        model.addAttribute("regdate", LocalDateTime.now());
        return "chap03/ex04";
    }

    //ex05
    @GetMapping("/ex05")
    public String ex05() {
        return "chap03/ex05";
    }
}

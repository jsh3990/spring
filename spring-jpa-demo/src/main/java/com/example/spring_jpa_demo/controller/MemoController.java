package com.example.spring_jpa_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemoController {

    @GetMapping("/memoList")
    public String memo() {
        return "memoList";
    }

}

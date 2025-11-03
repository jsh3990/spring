package com.example.basic.chap05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chap05")
public class MemoController {

    @Autowired
    @Qualifier("memoService")
    private MemoService memoService;

    //메모 리스트
    @GetMapping("/memoList")
    public String memo(Model model) {
        List<MemoVO> memo = memoService.list();
        model.addAttribute("memo", memo);
        return "chap05/memoList";
    }

    @GetMapping("/memoWrite")
    public String memoWrite() { return "chap05/memoWrite";}
    @PostMapping("/memoRegist")
    public String memoRegist(MemoVO memoVO) {
        memoService.memoRegist(memoVO);
        return "redirect:/chap05/memoList";
    }
}

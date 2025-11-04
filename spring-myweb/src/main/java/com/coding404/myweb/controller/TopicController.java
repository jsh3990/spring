package com.coding404.myweb.controller;

import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.topic.TopicService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    @Qualifier("topicService")
    private TopicService topicService;

    @GetMapping("/topicDetail")
    public String topicDetail(@RequestParam("topicId") long topicId, Model model) {
        model.addAttribute("vo", topicService.topicDetail(topicId));
        return "topic/topicDetail";
    }

    @GetMapping("topicListAll")
    public String topicListAll(Criteria cri, Model model) {
        List<TopicVO> topicList = topicService.topicListAll(cri);
        int total = topicService.getTotal(cri);
        PageVO pageVO = new PageVO(cri, total);

        model.addAttribute("topicList", topicList);
        model.addAttribute("pageVO", pageVO);
        return "topic/topicListAll";
    }

    @GetMapping("/topicListMe")
    public String topicListMe(Criteria cri, Model model) {
        String topicWriter = "admin";
        List<TopicVO> topicList = topicService.topicListMe(topicWriter, cri);
        int total = topicService.getTotalMe(topicWriter);
        PageVO pageVO = new PageVO(cri, total);

        model.addAttribute("topicListMe", topicList);
        model.addAttribute("pageVO", pageVO);
        return "topic/topicListMe";
    }

    @GetMapping("/topicModify")
    public String topicModify(@RequestParam("topicId") long topicId, Model model) {
        TopicVO vo = topicService.topicDetail(topicId);
        model.addAttribute("vo", vo);
        return "topic/topicModify";
    }

    @GetMapping("/topicReg")
    public String topicReg() {
        return "topic/topicReg";
    }

    //글목록
    @PostMapping("/topicRegister")
    public String topicRegister(TopicVO topicVO) {
        System.out.println(topicVO.toString());
        topicService.topicRegist(topicVO);
        return "redirect:/topic/topicListAll";
    }

    @PostMapping("/topicModify")
    public String topicModify(TopicVO topicVO) {
        topicService.topicModify(topicVO);
        return "redirect:/topic/topicListMe";
    }

    @PostMapping("/topicDelete")
    public String topicDelete(TopicVO topicVO) {
        topicService.topicDelete(topicVO);
        return "redirect:/topic/topicListMe";
    }



}

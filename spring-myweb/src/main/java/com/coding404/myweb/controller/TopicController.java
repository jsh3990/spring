package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.command.TopicVO;
import com.coding404.myweb.product.ProductServiceImpl;
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
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/topicDetail")
    public String topicDetail() {
        return "topic/topicDetail";
    }

    @GetMapping("topicListAll")
    public String topicListAll(@ModelAttribute("cri") Criteria cri, Model model) {
        String topicWriter = "admin";
        List<TopicVO> topicList = topicService.getList(topicWriter, cri);
        int total = topicService.getTotal(topicWriter);
        PageVO pageVO = new PageVO(cri, total);

        model.addAttribute("topicList", topicList);
        model.addAttribute("pageVO", pageVO);
        return "topic/topicListAll";
    }

    @GetMapping("/topicListMe")
    public String topicListMe(Criteria cri, Model model) {
        String topicWriter = "admin";
        //List<TopicVO> topicList = topicService.getList(topicWriter);
        List<TopicVO> topicList = topicService.getList(topicWriter, cri);
        int total = topicService.getTotal(topicWriter);
        PageVO pageVO = new PageVO(cri, total);

        model.addAttribute("topicList", topicList);
        model.addAttribute("pageVO", pageVO);
        return "topic/topicListMe";
    }

    @GetMapping("/topicModify")
    public String topicModify() {
        return "topic/topicModify";
    }

    @GetMapping("/topicReg")
    public String topicReg() {
        return "topic/topicReg";
    }

    //글목록
    @PostMapping("/topicReg")
    public String topicReg(TopicVO topicVO) {
        topicService.topicReg(topicVO);
        return "topic/topicReg";
    }

    @PostMapping("/topicModify")
    public String topicModify(TopicVO topicVO) {
        topicService.topicModify(topicVO);
        return "topic/topicModify";
    }

    @PostMapping("/topicUpdate")
    public String topicUpdate(TopicVO topicVO, RedirectAttributes ra) {
        int result = topicService.topicUpdate(topicVO);
        if(result == 1) {
            ra.addFlashAttribute("msg", "상품이 수정 되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "상품 수정에 실패했습니다. 관리자 1552-3322로 연락해주세요");
        }
        return "redirect:/topic/topicModify?topicId=" + topicVO.getTopicId();
    }

    @PostMapping("/topicDelete")
    public String productDelete(@RequestParam("topicId") long topicId,
                                RedirectAttributes ra) {

        int result = topicService.topicDelete(topicId);
        if(result == 1) {
            ra.addFlashAttribute("msg", "상품이 삭제되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "상품 삭제에 실패했습니다. 관리자 1552-3322로 연락해주세요");
        }
        return "redirect:/topic/topicListMe";
    }



}

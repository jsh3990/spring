package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    //상세화면
    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") long prodId, Model model) {
        System.out.println("상품의 아이디: " + prodId);

        model.addAttribute("vo", productService.getDetail(prodId));
        return "/product/productDetail";
    }

    //목록화면
    @GetMapping("/productList")
    public String productList(Criteria cri, Model model){
        System.out.println(cri);

        String prodWriter = "decoy"; // 본인의 아이디라고 가정
        //List<ProductVO> list = productService.getList(prodWriter);
        List<ProductVO> list = productService.getList(prodWriter, cri);

        int total = productService.getTotal(prodWriter, cri);
        PageVO pageVO = new PageVO(cri,total); // 페이지네이션 계산

        model.addAttribute("list",list);
        model.addAttribute("pageVO",pageVO);

        return "/product/productList";
    }

    //등록화면
    @GetMapping("/productReg")
    public String productReg(){
        return "/product/productReg";
    }

    //상품등록
    @PostMapping("/prodRegist")
    public String prodRegist(ProductVO productVO,
                             RedirectAttributes ra){
        System.out.println(productVO.toString());
        int result = productService.prodRegist(productVO); // 성공시 1, 실패시 0
        if(result == 1){
            ra.addFlashAttribute("msg", "상품이 정상 등록 되었습니다.");
        }else{
            ra.addFlashAttribute("msg", "상품 등록에 실패했습니다.");
        }
        return "redirect:/product/productList";
    }

    //상품수정
    @PostMapping("/prodUpdate")
    public String prodUpdate(ProductVO productVO){
        int result = productService.prodUpdate(productVO); // 성공시 1, 실패시 0
        return "redirect:/product/productDetail?prodId=" + productVO.getProdId();
    }

    //상품삭제
    @PostMapping("/prodDelete")
    public String prodDelete(@RequestParam("prodId") long prodId,
                             RedirectAttributes ra){
        int result = productService.prodDelete(prodId);
        if(result == 1){
            ra.addFlashAttribute("msg", "상품이 삭제 되었습니다");
        }else{
            ra.addFlashAttribute("msg", "상품 삭제를 실패했습니다");
        }

        return "redirect:/product/productList";
    }
}
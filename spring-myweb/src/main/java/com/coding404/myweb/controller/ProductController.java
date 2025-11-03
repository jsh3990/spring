package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.ProductService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    //목록화면
    @GetMapping("/productList")
    public String productList(Model model) {
        String prodWriter = "abc123"; //본인의 아이디라고 가정
        List<ProductVO> prodList = productService.getList(prodWriter); //조회
        model.addAttribute("prodList", prodList); //모델에 저장

        System.out.println(prodList.toString());

        return "product/productList";
    }
    //등록화면
    @GetMapping("/productReg")
    public String productReg() {
        return "product/productReg";
    }
    //상세화면
    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") long prodId,
                                Model model) {
        ProductVO vo = productService.getDetail(prodId);
        model.addAttribute("vo", vo);
        return "product/productDetail";
    }
    //상품등록
    @PostMapping("/prodRegist")
    public String prodRegist(ProductVO productVO,
                             RedirectAttributes ra) {

        int result = productService.prodRegist(productVO); //성공시 1, 실패시 0
        if(result == 1) {
            ra.addFlashAttribute("msg", "상품이 정상 등록 되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "상품 등록에 실패했습니다. 관리자 1552-3322로 연락해주세요");
        }

        return "redirect:/product/productList";

    }
    //상품수정
    @PostMapping("/productUpdate")
    public String productUpdate(ProductVO productVO, RedirectAttributes ra) {
        int result = productService.prodUpdate(productVO); //성공시 1, 실패시 0
        if(result == 1) {
            ra.addFlashAttribute("msg", "상품이 수정 되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "상품 수정에 실패했습니다. 관리자 1552-3322로 연락해주세요");
        }
        return "redirect:/product/productDetail?prodId=" + productVO.getProdId(); //수정 이후에 상세화면으로
    }

    //상품삭제
    @PostMapping("/productDelete")
    public String productDelete(@RequestParam("prodId") long prodId,
                                RedirectAttributes ra) {

        int result = productService.prodDelete(prodId);
        if(result == 1) {
            ra.addFlashAttribute("msg", "상품이 삭제되었습니다.");
        } else {
            ra.addFlashAttribute("msg", "상품 삭제에 실패했습니다. 관리자 1552-3322로 연락해주세요");
        }
        return "redirect:/product/productList";
    }
}

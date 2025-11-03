package com.example.basic.chap04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chap04")
public class BookController {

    //1st -
    //private BookService bookService = new BookServiceImpl();

    //필드주입
      @Autowired
      @Qualifier("bookService")
      private BookService bookService;

    //생성자주입
//    private final BookService bookService;
//    public BookController(@Qualifier("bookService") BookService bookService) {
//        this.bookService = bookService;
//    }

    //목록화면
    @GetMapping("/list")
    public String list(Model model) {
        List<BookVO> list = bookService.list();
        model.addAttribute("list", list);
        return "chap04/list";
    }
    //등록화면
    @GetMapping("/new")
    public String newBook() {
        return "chap04/new";
    }
    //도서등록요청
    @PostMapping("/bookRegist")
    public String bookRegist(BookVO bookVO) {
        bookService.bookRegist(bookVO);
        return "redirect:/chap04/list"; // <-- 등록 이후에 리다이렉트로, 목록데이터를 가져나감
    }

    //book삭제
    @PostMapping("/bookDelete")
    public String bookDelete(@RequestParam("id") long id) {
        bookService.bookDelete(id);
        return "redirect:/chap04/list"; //삭제 이후에 목록으로
    }
}

package com.example.basic.chap04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//base패키지 밑에 클래스를 생성
@Service("bookService")
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookVO> list() {
        return bookMapper.list();
    }

    public void bookRegist(BookVO bookVO) {
        bookMapper.bookRegist(bookVO);
    }

    public void bookDelete(long id) {
        bookMapper.bookDelete(id);
    }

//    @Autowired
//    @Qualifier("bookRepository")
//    private BookRepository bookRepository;
//
//    @Override
//    public List<BookVO> list() {
//        List<BookVO> list = bookRepository.list();
//        return list;
//    }
//
//    @Override
//    public void bookRegist(BookVO bookVO) {
//        bookRepository.bookRegist(bookVO);
//    }
//
//    @Override
//    public void bookDelete(long id) {
//        bookRepository.bookDelete(id);
//    }
}

package com.example.basic.chap04;

import java.util.List;

public interface BookRepository {

    List<BookVO> list(); //도서조회
    void bookRegist(BookVO bookVO); //도서등록
    void bookDelete(long id);

}

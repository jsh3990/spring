package com.coding404.myweb.product;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

import java.util.List;

public interface ProductService {
    int prodRegist(ProductVO productVO); //등록
    //List<ProductVO> getList(String prodWriter); //조회
    List<ProductVO> getList(String prodWriter, Criteria cri);
    int getTotal(String prodWriter, Criteria cri); //전체 게시글 수

    ProductVO getDetail(long prodId); //상세조회
    int prodUpdate(ProductVO productVO); //상품수정
    int prodDelete(long prodId); //상품삭제

}

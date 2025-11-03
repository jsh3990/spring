package com.coding404.myweb.product;

import com.coding404.myweb.command.ProductVO;

import java.util.List;

public interface ProductService {
    int prodRegist(ProductVO productVO); //등록
    List<ProductVO> getList(String prodWriter); //조회
    ProductVO getDetail(long prodId); //상세조회
    int prodUpdate(ProductVO productVO); //상품수정
    int prodDelete(long prodId); //상품삭제

}

package com.coding404.myweb.product;

import com.coding404.myweb.command.*;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int prodRegist(ProductVO productVO); //등록
    int fileRegist(ProductUploadVO productUploadVO); //파일등록

    //List<ProductVO> getList(String prodWriter); //조회
    //파라미터가 2개 이상이면 @Param 으로 이름 명시
    List<ProductVO> getList(@Param("prodWriter") String prodWriter,
                            @Param("cri") Criteria cri);
    int getTotal(@Param("prodWriter") String prodWriter,
                 @Param("cri") Criteria cri); //전체 게시글수

    ProductVO getDetail(long prodId); //상세조회
    List<ProductUploadVO> getDetailFile(long prodId);
    int prodUpdate(ProductVO productVO); //상품수정
    int prodDelete(long prodId); //상품삭제

    List<CategoryVO> getCategory(); //1단 카테고리
    List<CategoryVO> getCategoryChild(CategoryVO categoryVO); //2단 카테고리


    //조인연습
    List<DemoMemberVO> manyToOne(); //N:1
    DemoMemberVO oneToMany();
}

package com.coding404.myweb.product;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    int prodRegist(ProductVO productVO, List<MultipartFile> files); //등록
    //List<ProductVO> getList(String prodWriter); //조회
    List<ProductVO> getList(String prodWriter, Criteria cri);
    int getTotal(String prodWriter, Criteria cri); //전체 게시글수

    ProductVO getDetail(long prodId); //상세조회
    List<ProductUploadVO> getDetailFile(long prodId); //상세조회(파일)

    int prodUpdate(ProductVO productVO); //상품수정
    int prodDelete(long prodId); //상품삭제

    List<CategoryVO> getCategory(); //1단 카테고리
    List<CategoryVO> getCategoryChild(CategoryVO categoryVO); //2단 카테고리
}

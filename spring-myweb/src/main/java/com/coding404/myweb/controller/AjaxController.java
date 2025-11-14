package com.coding404.myweb.controller;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@RestController
public class AjaxController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @Value("${project.upload.path}")
    private String uploadPath;

    //대분류 카테고리 조회
    @GetMapping("/getCategory")
    public ResponseEntity<List<CategoryVO>> getCategory() {
        List<CategoryVO> category = productService.getCategory();
        return new ResponseEntity<>(category, HttpStatus.OK); //데이터, 상태코드
    }

    //중~소분류 카테고리 조회,
    @GetMapping("/getCategoryChild/{groupId}/{categoryLv}/{categoryDetailLv}")
    public ResponseEntity<List<CategoryVO>> getCategoryChild(
            @PathVariable("groupId") String groupId,
            @PathVariable("categoryLv") int categoryLv,
            @PathVariable("categoryDetailLv") int categoryDetailLv) {

//        CategoryVO vo = CategoryVO.builder()
//                .groupId(groupId)
//                .categoryLv(categoryLv)
//                .categoryDetailLv(categoryDetailLv)
//                .build();
        CategoryVO vo = new CategoryVO();
        vo.setGroupId(groupId);
        vo.setCategoryLv(categoryLv);
        vo.setCategoryDetailLv(categoryDetailLv);
        List<CategoryVO> category = productService.getCategoryChild(vo);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    //파일 이미지 처리
    @GetMapping("/display/{filepath}/{uuid}/{filename}")
    public ResponseEntity<byte[]> display(@PathVariable("filepath") String filepath,
                                          @PathVariable("uuid") String uuid,
                                          @PathVariable("filename") String filename) {

        String path = uploadPath + "/" + filepath + "/" + uuid + "_" +  filename;
        File file = new File(path);

        //헤더객체(컨텐츠타입)
        HttpHeaders headers = new HttpHeaders();

        //파일의 바이트데이터 구해오기
        byte[] bytes = null;
        try {
            bytes = FileCopyUtils.copyToByteArray(file);
            headers.add("Content-Type", Files.probeContentType( file.toPath() ) );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}





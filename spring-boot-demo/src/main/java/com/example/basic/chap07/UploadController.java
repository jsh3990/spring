package com.example.basic.chap07;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chap07")
public class UploadController {

    @Value("${project.upload.path}")
    String uploadPath;

    //화면처리
    @GetMapping("/upload")
    public String upload() {
        return "chap07/upload";
    }

    //폴더 생성 메서드
    public String makeFolder() {
        String path = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        File file = new File(uploadPath + "/" + path);
        if (!file.exists()) {
            file.mkdirs(); //폴더를 생성
        }
        return path;
    }

    //단일파일 업로드
    @PostMapping("/upload_ok")
    public String upload_ok(@RequestParam("file") MultipartFile file) {
        // 1. 브라우저별로 사용자의 경로가 앞에 붙어서 들어오는 경우가 있기 때문에 \\기준으로 절삭
        // 2. 동일한 이름으로 올라오는것 처리
        // 3. 윈도우 시스템이 폴더 하나에 저장할 수 있는 파일의 개수가 65536개
        try {
            String originalFilename = file.getOriginalFilename(); //파일의 이름
            String filename = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            long filesize = file.getSize(); //파일사이즈
            byte[] arr = file.getBytes(); //파일의 바이트코드

            String uuid = UUID.randomUUID().toString(); //랜덤 문자열값
            String filePath = makeFolder(); //폴더 경로
            String path = uploadPath + "/" + filePath + "/" + uuid + "_" + filename;
            File saveFile = new File(path);
            file.transferTo(saveFile); //업로드처리

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "chap07/upload_ok";
    }

    //multiple옵션
    @PostMapping("/upload_ok2")
    public String upload_ok2(@RequestParam("files") List<MultipartFile> files) {

        try {
            for(MultipartFile file : files) {
                //위와 동일
                String originalFilename = file.getOriginalFilename(); //파일의 이름
                String filename = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

                long filesize = file.getSize(); //파일사이즈
                byte[] arr = file.getBytes(); //파일의 바이트코드

                String uuid = UUID.randomUUID().toString(); //랜덤 문자열값
                String filePath = makeFolder(); //폴더 경로
                String path = uploadPath + "/" + filePath + "/" + uuid + "_" + filename;
                File saveFile = new File(path);
                file.transferTo(saveFile); //업로드처리
            }
        } catch (Exception e) {

        }

        return "chap07/upload_o";
    }

    @PostMapping("/upload_ok3")
    public String upload_ok3(@RequestParam("file") List<MultipartFile> files) {
        //사용자가 올리지 않은 빈데이터 필터링
        files = files.stream()
                .filter(data -> data.isEmpty() == false)
                .collect(Collectors.toList() );
        try {
            for(MultipartFile file : files) {
                //위와 동일
                String originalFilename = file.getOriginalFilename(); //파일의 이름
                String filename = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

                long filesize = file.getSize(); //파일사이즈
                byte[] arr = file.getBytes(); //파일의 바이트코드

                String uuid = UUID.randomUUID().toString(); //랜덤 문자열값
                String filePath = makeFolder(); //폴더 경로
                String path = uploadPath + "/" + filePath + "/" + uuid + "_" + filename;
                File saveFile = new File(path);
                file.transferTo(saveFile); //업로드처리
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "chap07/upload_ok";
    }

    //비동기 파일 업로드
    @PostMapping("/upload_ok4")
    @ResponseBody //응답이 요청온 곳으로
    public String upload_ok4(@RequestParam("file") MultipartFile file) {

        try {
                String originalFilename = file.getOriginalFilename(); //파일의 이름
                String filename = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

                long filesize = file.getSize(); //파일사이즈
                byte[] arr = file.getBytes(); //파일의 바이트코드

                String uuid = UUID.randomUUID().toString(); //랜덤 문자열값
                String filePath = makeFolder(); //폴더 경로
                String path = uploadPath + "/" + filePath + "/" + uuid + "_" + filename;
                File saveFile = new File(path);
                file.transferTo(saveFile); //업로드처리
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }
}

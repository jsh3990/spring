package com.example.basic.chap06;

import oracle.jdbc.proxy.annotation.Post;
import org.apache.ibatis.javassist.Loader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller //완전히 다른 방식으로 동작함
@RestController // Controller + ResponseBody
@RequestMapping("/chap06")
public class RestBasicController {

    //@ResponseBody //요청 온 곳으로 응답함, 자바의 object를 JSON 형식으로 자동 변환
    @GetMapping("/basic")
    public String basic() {
        return "하이하이";
    }

    //데이터를 응답하는 방법
    //1. object
    @GetMapping("/getObject")
    public SimpleVO getObject() {
         SimpleVO simpleVO = new SimpleVO(1, "홍", "길동", LocalDateTime.now());
         return simpleVO;
    }

    //2. map
    @GetMapping("/getMap")
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "홍길동");
        map.put("hobby", new String[] {"밥", "먹", "기"} );
        map.put("profile", new SimpleVO(1, "홍", "길동", LocalDateTime.now()) );
        return map;
    }

    //3. List
    @GetMapping("/getList")
    public List<SimpleVO> getList() {
        List<SimpleVO> list = new ArrayList<>();
        list.add( new SimpleVO(1, "홍", "길동", LocalDateTime.now() ));
        list.add( new SimpleVO(1, "홍", "길동", LocalDateTime.now() ));
        return list;
    }

    //데이터를 받는 방법
    //get 주소를 통해서 받음 -> 쿼리스트링(?) or 쿼리파라미터(url 주소방식)
    //post 바디에 데이터를 담아서 보냄 -> 폼형식 or JSON

    //http://localhost:8181/chap06/getData?id=홍길동&age=20
    @GetMapping("/getData")
    public SimpleVO getData(@RequestParam("id") String id,
                            @RequestParam("age") int age) {
        System.out.println(id + "," + age);
        return new SimpleVO(1, "홍", "길동", LocalDateTime.now());
    }

    //http://localhost:8181/chap06/getData2?num=1&first=이순신
    @GetMapping("/getData2")
    public String getData2(SimpleVO simpleVO) {
        System.out.println(simpleVO.toString());
        return "success";
    }

    //http://localhost:8181/chap06/getData3/홍길동/20
    //@CrossOrigin("http://127.0.0.1:5501") //이 주소에서 오는 요청은 하겠다.
    @CrossOrigin("*")
    @GetMapping("/getData3/{name}/{age}")
    public String getData3(@PathVariable("name") String name,
                           @PathVariable("age") int age) {
        System.out.println(name + "," + age);
        return "success";
    }

    //post
    //클라이언트에서 form형식으로 데이터를 보낼 수 있음
    @PostMapping("/getForm")
    public String getForm(SimpleVO simpleVO) {
        System.out.println(simpleVO.toString());
        return "success";
    }
    //클라이언트에서 json형식으로 데이터를 보낼 수 있음, RequestBody -> JSON을 object로 맵핑
//    @PostMapping("/getJSON")
//    public String getJSON(@RequestBody SimpleVO simpleVO) {
//        System.out.println(simpleVO.toString());
//        return "success";
//    }
    @CrossOrigin("http://127.0.0.1:5501")
    @PostMapping("/getJSON")
    public String getJSON(@RequestBody Map<String, Object> map) {
        System.out.println(map.toString());
        return "success";
    }

    //consumer - 반드시 데이터를 이 타입으로 보내
    //producer - 내가 이 타입으로 줄게
    @PostMapping(value = "/getResult", consumes = "text/plain", produces = "text/plain")
    public String getResult(@RequestBody String str) {
        System.out.println(str);
        return "<h3>success</h3>";
    }

    //ResponseEntity<반환타입> - 응답문서 상세히 작성하기
    @PostMapping("/getEntity")
    public ResponseEntity<SimpleVO> 메서드명() {

        SimpleVO simpleVO = new SimpleVO(1, "hong", "dong", null );
        //상태코드만 반환
        //ResponseEntity<SimpleVO> entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);

        //데이터와 상태코드 반환
        //ResponseEntity<SimpleVO> entity = new ResponseEntity<>( simpleVO, HttpStatus.FORBIDDEN);

        //데이터와 헤더 상태코드 변환
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Authorization", "Bearer JSONWEBTOKEN");
        ResponseEntity<SimpleVO> entity = new ResponseEntity<>( simpleVO, headers, HttpStatus.OK);
        return entity;
    }

    /// ///////////////////////////////


    /*
	클라이언트 fetch
	요청주소 : /chap06/api/v1/getData
	메서드 : get
	클라이언트에서 보낼데이터는 : num, name을 쿼리스트링 방식으로
	서버에서 보낼데이터는 : SimpleVO
	받을 수 있는 restAPI를 생성
	*/
    @CrossOrigin("http://127.0.0.1:5501")
    @GetMapping("/api/v1/getData")
    public ResponseEntity <SimpleVO> getDataV1(@RequestParam("num") int num,
                                               @RequestParam("name") String name) {
        SimpleVO simpleVO = new SimpleVO(1, "t", "t", null );
        System.out.println(num + "," + name);
        ResponseEntity<SimpleVO> responseentity = new ResponseEntity<>(simpleVO, HttpStatus.OK);
        return responseentity;
    }

	/*
	 클라이언트 fetch
	 요청주소 : /chap06/api/v1/getInfo
	 메서드 : post
	 클라이언트에서 보낼데이터는 : num, first
	 서버에서 보낼데이터는 : 리스트<SimpleVO>
	 받을 수 있는 restAPI를 생성
	 ResponseEntity로 응답
	 */
    //클라이언트에서 form 타입 보냄
    @CrossOrigin("http://127.0.0.1:5501")
    @PostMapping("/api/v1/getInfo")
    public ResponseEntity<List<SimpleVO>> getInfo(SimpleVO simpleVO) {

        System.out.println(simpleVO.toString());
        List<SimpleVO> list = new ArrayList<>();
        list.add( new SimpleVO(1, "홍", "길동", LocalDateTime.now() ));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }




}

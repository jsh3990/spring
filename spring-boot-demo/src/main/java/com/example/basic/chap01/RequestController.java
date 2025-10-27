package com.example.basic.chap01;

import com.example.basic.chap01.command.ReqVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Arrays;

@Controller //com.example.basic 패키지 아래에 있는 이 어노테이션이 붙어있는 파일들을 자동으로 객체로 생성해줌
@RequestMapping("/chap01")
public class RequestController {

    //@RequestMapping("/hello")
    //@RequestMapping("/chap01/ex01")
    @RequestMapping("/ex01")
    public String req_ex01() {
        System.out.println("컨트롤러 타고 나감");
        return "chap01/ex01";
    }

    //basic01요청 - 어노테이션에서 2개이상의 속성을 사요하려면, 키를 붙여주면 된다.
    //메서드 방식을 허용하지 않으면, 둘 다 허용시킴
    //get요청만 허용함
    //@RequestMapping(value = "/basic01", method= RequestMethod.GET )
    @GetMapping("/basic01")
    public String basic01() {
        System.out.println("basic01메서드 동작됨");
        return null;
    }

    //post요청만 허용함
    //@RequestMapping(value = "/basic02", method= RequestMethod.POST )
    @PostMapping("/basic02")
    public String basic02() {
        System.out.println("basic02메서드 동작됨");
        return null;
    }

    //각 요청을 집합으로 묶어서 사용할 수 있음
//    @RequestMapping({"/basic01", "basic02", "생략..."})
//    public void basic() {
//        System.out.println("basic01~02요청 처리");
//    }

    //-------------------------------------------------
    //void형으로 만들면 요청경로가 응답경로가 됩니다.
    @GetMapping("/ex02")
    public String ex02() {
        return "chap01/ex02";
    }

    @GetMapping("/redirect")
    public String redirect() {
        //return "chap01/ex01"; //기본이동은 forward방식을 따라감
        return "redirect:/chap01/ex01"; //다시 컨트롤러를 태워나갈때 쓴다. (ex-게시글에서 데이터 들고 나갈때)
    }


    @GetMapping("/redirect2")
    public String redirect2() {
        return "redirect:/";
    }

    //---------------------------------------
    //파라미터받기1
//    @PostMapping("/param")
//    public String param01(HttpServletRequest request) {
//
//        String id = request.getParameter("id");
//        String pw = request.getParameter("pw");
//        String name = request.getParameter("name");
//        String[] inter = request.getParameterValues("inter");
//
//        System.out.println(id + "," + pw + "," + name + "," + Arrays.toString(inter));
//
//        return "chap01/ex02_ok"; //결과화면
//    }

     //파라미터 받기2
    //RequestParam은 반드시 화면에서 데이터를 넘겨야 되는데, 넘기지 않더라도 허용하려면 required 옵션을 쓴다.
    //defaultValue는 값이 넘어오지 않을 때 기본값을 지정
//    @PostMapping("/param")
//    public String param01(@RequestParam("id") String id,
//                          @RequestParam("pw") String pw,
//                          @RequestParam("name") String name,
//                          @RequestParam(value="inter", required = false, defaultValue = "기본값") String[] arr) {
//
//        System.out.println(id + "," + pw + "," + name + "," + Arrays.toString(arr));
//
//        System.out.println(arr[0]);
//
//        return "chap01/ex02_ok"; //결과화면
//    }

    //파라미터 받기3 - VO객체 사용
    @PostMapping("/param")
    public String param01(ReqVO vo) {

        System.out.println(vo.toString());

        return "chap01/ex02_ok"; //결과화면
    }

    @RequestMapping("/quiz01")
    public String quiz01() {
        return "chap01/quiz01";
    }

    @PostMapping("/login")
    public String quiz01_ok(@RequestParam("id") String id,
                            @RequestParam("pw") String pw) {
        if (id.equals("abc123") && pw.equals("xxx123")) {
            return "chap01/quiz01_ok";
        } else {
            return "chap01/quiz01_no";
        }
    }

    //vo사용하는 법
//    @PostMapping("/login")
//    public String login(ReqVO vo) {
//
//        String id = vo.getId();
//        String pw = vo.getPw();
//
//        if(id.equals("abc123") && pw.equals("xxx123")) {
//            return "chap01/quiz01_ok";
//        } else {
//            return "chap01/quiz01_no";
//        }
//    }


}







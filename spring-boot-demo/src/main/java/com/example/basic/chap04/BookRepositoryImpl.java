package com.example.basic.chap04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("bookRepository")
public class BookRepositoryImpl implements BookRepository {

    /*
    데이터베이스 연결 정보
    디비 드라이버 클래스 이름
    유저아이디
    유저패스워드
    디비 연결주소

     */
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public List<BookVO> list() {

        //select 결과를 담을 list
        List<BookVO> list = new ArrayList<>();


        String sql = "SELECT * FROM BOOK ORDER BY ID DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; //select에서만 필요 - 실행결과를 받아올 객체

        try {
            //1. 드라이브 클래스 준비
            Class.forName(driver);
            //2. 커넥션객체(연결객체)
            conn = DriverManager.getConnection(url, username, password);
            //3. stmt
            pstmt = conn.prepareStatement(sql);
            //4. sql실행 (select문자는 executeQuery 실행)
            rs = pstmt.executeQuery();
            //rs에는 sql 실행 결과가 저장돼 있음

            //rs.next() - 다음 실행결과가 있는지 확인하고, 있으면 다음 행으로 접근
            while(rs.next()) {
                //1. 1행에 대한 처리 - rs.getString, rs.getInt, rs.getDate
                long id = rs.getLong("id"); //id컬럼
                String title = rs.getString("title"); //title컬럼
                String author = rs.getString("author"); //author컬럼

                BookVO bookVO = new BookVO(id, title, author);
                list.add(bookVO); //리스트에 레코드 저장
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
                if(rs != null) rs.close();
            } catch (Exception e) {}
        }

        return list;
    }

    @Override
    public void bookRegist(BookVO bookVO) {
        //JDBC
        String sql = "INSERT INTO BOOK(ID, TITLE, AUTHOR) VALUES( BOOK_SEQ.NEXTVAL, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //1. 드라이브 클래스 준비
            Class.forName(driver);
            //2. 커넥션객체(연결객체)
            conn = DriverManager.getConnection(url, username, password);
            //3. state객체를 얻음 (sql전달을 위한 객체)
            pstmt = conn.prepareStatement(sql);
            //4. ?값을 채운다 (?는 순서대로 1번부터 시작함)
            pstmt.setString(1, bookVO.getTitle() );
            pstmt.setString(2, bookVO.getAuthor());
            //5. 실행 - execueUpdate(u, d, i 구문에 사용함) , excuteQuery(select문에 사용함)
            int result = pstmt.executeUpdate(); //성공실패 여부를 반환

            if(result == 1) { //성공시 insert된 행의 개수
                System.out.println("인서트 성공");
            } else {
                System.out.println("인서트 실패");
            }

            pstmt.close();
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void bookDelete(long id) {

    }

    /*
    //가짜 데이터 베이스 ArrayList
    private List<BookVO> list = new ArrayList<>();
    private long newId = 1; //book데이터를 관리할 pk

    @Override
    public List<BookVO> list() {
        //데이터베이스 연결해서 처리
        return list;
    }

    @Override
    public void bookRegist(BookVO bookVO) {
        bookVO.setId(newId);
        newId++;
        list.add(bookVO);
        System.out.println(bookVO.toString());
    }

    @Override
    public void bookDelete(long id) {
        System.out.println("넘어온 값: " + id);
        //삭제
        for(int i = 0; i < list.size(); i++) {
            BookVO vo = list.get(i);
            if(vo.getId() == id) {
                list.remove(i); //id가 같으면, 인덱스 번째 데이터 삭제
                break;
            }
        }
    }
      */
}

package com.example.spring_jpa_demo.repository;

import com.example.spring_jpa_demo.command.MemberMemoDTO;
import com.example.spring_jpa_demo.entity.Member;
import com.example.spring_jpa_demo.entity.Memo;
import com.example.spring_jpa_demo.entity.QMemo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MemoCustomRepositoryImpl implements MemoCustomRepository {

    //엔티티 매니저 - 영속성 영역에 접근 가능한 객체
    @PersistenceContext
    private EntityManager entityManager;

    //쿼리DSL을 사용하기 위한 팩토리
    private JPAQueryFactory jpaQueryFactory;
    public MemoCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    @Transactional
    public int updateTest(String writer, String text) {
        //자바 관련 프로그램 코드 작성
        String sql = "update Memo m set m.writer = :a where m.writer = :b"; //JPQL

        Query query = entityManager.createQuery(sql); //sql구문 준비
        query.setParameter("a", text); //a 변수에 text값 세팅
        query.setParameter("b", writer); //b 변수에 writer값 세팅
        //insert, update, delete는 executeUpdate문을 사용함
        //select getResultList, getSingleResult를 사용함
        int result = query.executeUpdate();
        return result;

    }

    @Override
    public List<Memo> mtoJoin1() {
        String sql = "select m from Memo m left join m.member";
        //실행결과는 Query 또는 TypeQuery로 받을 수 있음
        TypedQuery<Memo> query = entityManager.createQuery(sql, Memo.class); //반환타입
        List<Memo> list = query.getResultList(); //한행이라면 getSingleResult로 받음
        return list;
    }

    //두개 이상의 엔티티를 선택적으로 가지고 나올때 Object[] 타입으로 처리
    @Override
    public List<Object[]> mtoJoin2(long id) {
        String sql = "select m, x from Memo m inner join m.member x where m.id >= :id";
        TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class); //요기
        query.setParameter("id", id);
        List<Object[]> result = query.getResultList(); //한행에 대한 조회
        return result;
    }

    //writer와 name은 서로 키관계가 없는 상태
    //연관관계가 없는 엔티티를 조인할 때 ON절을 사용함
    @Override
    public List<Object[]> mtoJoin3(String writer) {
        String sql = "select m, x from Memo m inner join Member x on m.writer = x.name where m.writer = :writer";
        TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class );
        query.setParameter("writer", writer);
        return query.getResultList();
    }

    //로딩전략 Eager, Lazy
    //N + 1 문제 - Eager조인시 발생함 (메인쿼리를 수행하고, 붙을 수 있는 데이터를 확인하기위해 여러번 select를 하게됨)
    //해결방법1 - Lazy로딩을 선택함
    //해결방법2 - Fetch조인으로 처리함
    @Override
    public List<Memo> mtoJoin4() {
        String sql = "select m from Memo m inner join fetch m.member x"; //조인구문 뒤에 fetch
        TypedQuery<Memo> query = entityManager.createQuery(sql, Memo.class);
        return query.getResultList();
    }

    @Override
    public Member oTmJoin1(String id) {
        String sql = "select m from Member m join m.list x where m.id = :id";
        TypedQuery<Member> query = entityManager.createQuery(sql, Member.class);
        query.setParameter("id", id);
        Member m = query.getSingleResult(); //1행을 select할때
        return m;
    }

    //원투매니 fetch
    //1:N조인에서 fetch 사용시 행 중복현상이 발생할 수 있음
    //distinct키워드를 붙여서 중복행 제거
    @Override
    public List<Member> oTmJoin2(String id) {
        String sql = "select distinct m from Member m join fetch m.list x where m.id = :id";
        TypedQuery<Member> query = entityManager.createQuery(sql, Member.class);
        query.setParameter("id", id);
        return query.getResultList(); //여러행
    }

    //DTO로 처리하기
    //반환받을 값이 여러행이라면 List<MemberMemoDTO>
    @Override
    public MemberMemoDTO oTmJoin3(String id) {

        //select절에는 MemberMemoDTO(생성자에 전달될값)
        String sql = "select new com.example.demo.command.MemberMemoDTO(m.id, m.name, m.signDate, x.writer, x.text)" +
                " from Member m join m.list x where m.id = :id";

        TypedQuery<MemberMemoDTO> query = entityManager.createQuery(sql, MemberMemoDTO.class);
        query.setParameter("id", id);
        return query.getSingleResult(); //한행
    }


    //쿼리DSL 기본문법
    public Memo dslSelect() {

        QMemo memo = QMemo.memo; //쿼리DSL을 자바sql문을 쓰기위한 클래스
        Memo m = jpaQueryFactory.select(memo) //select memo
                .from(memo) //from memo
                .where( memo.id.eq(10L) ) // where m.id = 10
                .fetchOne(); //1행조회
        return m;
    }

    @Override
    public List<Memo> dslSelect2() {

        QMemo memo = QMemo.memo;
        List<Memo> list = jpaQueryFactory.select(memo)
                .from(memo)
                //.where( memo.text.like("%2%") )
                //.where( memo.id.gt(10).and( memo.id.lt(20) )) // where id > 10 and id < 20
                .where ( memo.id.goe(10).or( memo.id.loe(20) )) //where id >= 10 or id <= 20
                .orderBy( memo.id.desc() )
                .fetch(); //여러행 조회

        //fetch() 여러행 조회, fetchOne() 단일행 execute() 인서트 업데이트 딜리트

        return list;
    }

    @Override
    public List<Memo> dslSelect3(String searchType, String searchName) {

        QMemo memo = QMemo.memo;
        //조건절을 불린빌더에 조합할 수 있습니다.
        BooleanBuilder builder = new BooleanBuilder();
        //검색의 조건이 writer면 writer like
        if(searchType != null && searchType.equals("writer") ) {
            builder.and( memo.writer.like( "%" + searchName + "%" ) );
        }
        //검색의 조건이 text면 text like
        if(searchType != null && searchType.equals("text") ) {
            builder.and( memo.text.like( "%" + searchName + "%" ) );
        }
        //...생략....
        List<Memo> list = jpaQueryFactory.select(memo)
                .from(memo)
                .where( builder )
                .fetch();

        return list;
    }
}

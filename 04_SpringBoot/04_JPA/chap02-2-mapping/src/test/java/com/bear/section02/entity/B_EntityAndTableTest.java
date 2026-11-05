package com.bear.section02.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * [2. Entity 와 Table]
 *
 * 실행: 클래스 이름 옆 ▶ (또는 ./gradlew test --tests "com.bear.section02.*")
 *
 * 볼 것
 *  1) JPQL 은 "엔티티 이름 + 필드 이름", SQL 은 "테이블 이름 + 컬럼 이름"을 쓴다
 *  2) 조회할 때 Hibernate 가 기본 생성자로 객체를 만든다
 */
public class B_EntityAndTableTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("mappinglab");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @Test
    public void JPQL은_엔티티_이름을_SQL은_테이블_이름을_쓴다() {

        // given: 회원 1명 저장 (commit 까지)
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(new LabMember("b-user01", "곰"));
        transaction.commit();
        entityManager.clear();   // 1차 캐시를 비워서 아래 조회가 진짜 DB 에서 오게 한다

        // when ①: JPQL — 엔티티 이름(LabMember) 과 필드 이름(memberId)
        //   createQuery(JPQL, 결과타입) / :memberId 는 이름 있는 파라미터 / getResultList() 는 결과 목록
        String jpql = "SELECT m FROM LabMember m WHERE m.memberId = :memberId";
        List<LabMember> byJpql = entityManager.createQuery(jpql, LabMember.class)
                .setParameter("memberId", "b-user01")
                .getResultList();
        System.out.println(">>> JPQL : " + jpql);
        System.out.println(">>> 결과 : " + byJpql);
        // 콘솔: Hibernate 가 JPQL 을 "select ... from lab_member lm1_0 where lm1_0.member_id=?" 로 바꿔서 실행

        // when ②: SQL — 테이블 이름(lab_member) 과 컬럼 이름(member_id)
        String sql = "SELECT nickname FROM lab_member WHERE member_id = :memberId";
        Object nickname = entityManager.createNativeQuery(sql)
                .setParameter("memberId", "b-user01")
                .getSingleResult();
        System.out.println(">>> SQL  : " + sql);
        System.out.println(">>> 결과 : " + nickname);

        // then
        assertEquals(1, byJpql.size());
        assertEquals("곰", byJpql.get(0).getNickname());
        assertEquals("곰", nickname);

        // 바꿔 보기: jpql 의 LabMember 를 lab_member 로 바꾸면? → 엔티티 이름이 아니라서 JPQL 해석 단계에서 예외
    }

    @Test
    public void 조회할_때_Hibernate가_기본_생성자로_객체를_만든다() {

        // given
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        LabMember saved = new LabMember("b-user02", "호랑이");   // ← 우리 생성자 (기본 생성자 문장 안 찍힘)
        entityManager.persist(saved);
        transaction.commit();
        entityManager.clear();

        System.out.println(">>> 지금부터 find() — 아래에 '기본 생성자 호출' 문장이 찍히는지 보자");

        // when: 1차 캐시가 비었으므로 SELECT → 결과 행으로 객체 생성 (기본 생성자 → 필드 채우기)
        LabMember found = entityManager.find(LabMember.class, saved.getMemberNo());

        // then
        System.out.println(">>> found = " + found);
        assertNotSame(saved, found);              // clear() 했으므로 저장한 객체와 다른 새 객체
        assertEquals("호랑이", found.getNickname());
    }

    /*
     * 바꿔 보기 (하나씩 바꾸고 이 클래스를 다시 실행 → 원래대로 되돌리기)
     *  1) LabMember 의 기본 생성자를 지운다
     *     → 엔티티 조건 위반. EMF 를 만들 때나 조회할 때 기본 생성자가 없다는 예외가 난다.
     *  2) LabMember 클래스에 final 을 붙인다 (public final class LabMember)
     *     → 엔티티 조건 위반. Hibernate 가 경고 또는 예외를 낸다 (콘솔 메시지 확인).
     *  3) LabMember 의 @Entity(name = "LabMember") 를 @Entity(name = "SchemaMember") 로 바꾼다
     *     → 같은 영속성 단위(mappinglab)에 같은 엔티티 이름이 두 개 → EMF 생성 단계에서 예외.
     */
}

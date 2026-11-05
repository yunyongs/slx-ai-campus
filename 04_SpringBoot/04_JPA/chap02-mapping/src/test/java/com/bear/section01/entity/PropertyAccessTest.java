package com.bear.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class PropertyAccessTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }
    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }
    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void 테이블_만들기_테스트() {
        // given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("user01");
        member.setNickname("bear");

        // when
        entityManager.persist(member); // 영속 상태 (Commit 안 했기 때문에 저장은 안 됨)

        //그러나 데이터베이스는 만들어짐 -> 엔티티 매니저 팩토리가 만들어질때 create table이 실행되어 커밋까지 끝남

        Member foundMember = entityManager.find(Member.class, member.getMemberNo()); // 1차 캐시에서 찾아옴
        System.out.println("foundMember = " + foundMember);
    }


}

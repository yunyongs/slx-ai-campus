package com.bear.section03.field;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldAccessTest {
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
    public void 필드_접근_테스트() {

        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");

        entityManager.persist(member);

        // 리플렉션
        // - 실행중에 클래스 정보를 확인하고 필드, 메서드에 접근하는 자바 기능
        // - private 필드도 정보를 가져올 수 있음
        Member foundMember = entityManager.find(Member.class, 1);

        assertEquals(member, foundMember);
//        System.out.println(foundMember);

    }

}

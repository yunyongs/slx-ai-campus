package com.bear.practice;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Practice {

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
    @Disabled("문제 01")
    public void 문제01_엔티티명은_클래스명과_같을까() {
        Entity entity = EntityNameMember.class.getAnnotation(Entity.class);

        assertEquals("Member", entity.name());
    }

    @Test
    @Disabled("문제 02")
    public void 문제02_트랜지언트_필드도_데이터베이스에서_조회될까() {
        ColumnMember member = new ColumnMember();
        member.setMemberNo(201);
        member.setMemberId("user201");
        member.setMemberPwd("pass201");
        member.setNickname("홍길동");
        member.setAddress("서울시 종로구");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();
        entityManager.clear();

        ColumnMember foundMember = entityManager.find(ColumnMember.class, 201);

        assertEquals("홍길동", foundMember.getNickname());
    }

    @Test
    @Disabled("문제 03")
    public void 문제03_아이덴티티_전략은_persist_직후_기본키가_생길까() {
        IdentityMember member = new IdentityMember();
        member.setMemberId("user301");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);

        assertTrue(member.getMemberNo() > 0);

        entityTransaction.commit();
    }

    @Test
    @Disabled("문제 04")
    public void 문제04_문자열_방식의_열거형은_이름으로_저장될까() {
        EnumMember member = new EnumMember();
        member.setMemberNo(401);
        member.setMemberId("user401");
        member.setMemberRole(RoleType.ADMIN);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();
        entityManager.clear();

        Object storedRole = entityManager.createNativeQuery(
                        "SELECT member_role FROM tbl_practice_enum_member WHERE member_no = 401")
                .getSingleResult();

        assertEquals("ADMIN", storedRole);
    }

    @Test
    @Disabled("문제 05")
    public void 문제05_프로퍼티_접근_엔티티는_필드로_접근할까() {
        Access access = PropertyAccessMember.class.getAnnotation(Access.class);

        assertEquals(AccessType.FIELD, access.value());
    }
}

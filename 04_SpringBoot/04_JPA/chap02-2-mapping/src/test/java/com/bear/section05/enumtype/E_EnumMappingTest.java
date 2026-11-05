package com.bear.section05.enumtype;

import com.bear.common.SchemaPrinter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 * [5. Enum Type]
 *
 * 실행: 클래스 이름 옆 ▶ (또는 ./gradlew test --tests "com.bear.section05.*")
 *
 * 흐름: MEMBER 로 저장(commit) → DB 의 "날 것" 값을 SQL 로 읽기 → clear() 후 JPA 로 읽어서 Enum 으로 복원되는지 확인
 */
public class E_EnumMappingTest {

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
    public void ORDINAL은_숫자로_STRING은_이름으로_저장된다() {

        // 컬럼 타입부터: role_default/role_ordinal 은 숫자 타입, role_string 은 enum(...) 또는 varchar
        SchemaPrinter.printColumns(entityManager, "lab_enum_member");

        // given: MEMBER (선언 순서 1번째 → ORDINAL 값 1)
        EnumMember member = new EnumMember(RoleType.MEMBER);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(member);
        transaction.commit();

        // when ①: DB 에 실제로 들어간 값 (SQL 로 직접)
        Object[] raw = (Object[]) entityManager.createNativeQuery(
                        "SELECT role_default, role_ordinal, role_string FROM lab_enum_member WHERE member_no = :memberNo")
                .setParameter("memberNo", member.getMemberNo())
                .getSingleResult();
        System.out.println(">>> DB 의 날 것 값: role_default = " + raw[0] + ", role_ordinal = " + raw[1] + ", role_string = " + raw[2]);

        // when ②: JPA 로 다시 읽기 (clear 해서 진짜 DB 에서)
        entityManager.clear();
        EnumMember found = entityManager.find(EnumMember.class, member.getMemberNo());
        System.out.println(">>> JPA 로 복원한 값: " + found.getRoleDefault() + ", " + found.getRoleOrdinal() + ", " + found.getRoleString());

        // then
        assertEquals(1, ((Number) raw[0]).intValue());    // 생략 = ORDINAL
        assertEquals(1, ((Number) raw[1]).intValue());    // ORDINAL: MEMBER 는 1
        assertEquals("MEMBER", String.valueOf(raw[2]));    // STRING: 이름 그대로
        assertEquals(RoleType.MEMBER, found.getRoleOrdinal());
        assertEquals(RoleType.MEMBER, found.getRoleString());

        // 바꿔 보기: RoleType 의 순서를 MEMBER, ADMIN 으로 바꾸고, 위 assertEquals 들을 지운 뒤 다시 실행해 보면
        // → 이 클래스는 매번 테이블을 새로 만들어서 문제가 안 보인다. 실제 서비스에서는 "옛날에 저장된 1" 이 의미가 바뀐다는 점이 위험.
    }
}

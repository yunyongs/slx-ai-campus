package com.bear.section01.schema;

import com.bear.common.SchemaPrinter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/*
 * [1. 스키마 자동 생성]
 *
 * 실행: 클래스 이름 옆 ▶ 클릭 (또는 터미널: ./gradlew test --tests "com.bear.section01.*")
 *
 * 전체 흐름
 *  @BeforeAll initFactory()
 *     → persistence.xml("mappinglab") 읽기 → hbm2ddl.auto=create
 *     → lab_* 테이블 전부 DROP → CREATE   (콘솔 맨 위에 DDL 이 쭉 찍힌다)
 *  @BeforeEach initManager()  → 테스트마다 새 EntityManager
 *  @Test ...                  → 아래 4개
 *  @AfterEach / @AfterAll     → EM 닫기 / EMF 닫기
 */
public class A_SchemaAutoGenerationTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        System.out.println("===== EMF 생성 시작: 이 아래로 hbm2ddl.auto=create 가 실행한 DDL 이 찍힌다 =====");
        entityManagerFactory = Persistence.createEntityManagerFactory("mappinglab");
        System.out.println("===== EMF 생성 끝 =====");
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
    public void create_모드로_엔티티_기준_테이블이_만들어진다() {

        // when: MySQL 시스템 테이블에서 lab_schema_member 의 컬럼 구조를 읽는다
        int columnCount = SchemaPrinter.printColumns(entityManager, "lab_schema_member");

        // then: SchemaMember 의 필드 3개 → 컬럼 3개 (member_no, member_id, nickname)
        assertEquals(3, columnCount);

        // 콘솔에서 볼 것
        //  - member_no : PRI (← @Id), auto_increment (← IDENTITY)
        //  - member_id : varchar(30), NULL 허용 NO (← length = 30, nullable = false)
        //  - nickname  : varchar(255), NULL 허용 YES (← 기본값)
    }

    @Test
    public void 테이블_생성과_데이터_저장은_별개다() {

        // given: 지금 테이블에 있는 행 수
        long before = SchemaPrinter.countRows(entityManager, "lab_schema_member");

        // when: 저장했다가 commit 대신 rollback
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        SchemaMember member = new SchemaMember("schema-user01", "곰");
        entityManager.persist(member);   // IDENTITY 라서 INSERT 는 지금 바로 실행된다 (콘솔 확인)
        System.out.println(">>> persist 직후 memberNo = " + member.getMemberNo() + " (INSERT 는 실행됐지만 아직 확정 전)");

        transaction.rollback();          // 확정하지 않고 취소 → INSERT 도 취소
        entityManager.clear();

        long after = SchemaPrinter.countRows(entityManager, "lab_schema_member");
        System.out.println(">>> 행 수: rollback 전 " + before + " → rollback 후 " + after);

        // then: 테이블은 있지만(create 가 만듦) 데이터는 저장되지 않았다
        assertEquals(before, after);
    }

    @Test
    public void validate_모드는_구조가_맞으면_통과한다() {

        // given: 이 EMF 하나만 hbm2ddl.auto 를 validate 로 바꿔서 만든다 (persistence.xml 값을 코드에서 덮어쓰기)
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "validate");

        // when: validate 는 DDL 을 실행하지 않고, 엔티티 매핑과 실제 테이블 구조가 같은지 검사만 한다
        EntityManagerFactory validateFactory = Persistence.createEntityManagerFactory("mappinglab", properties);

        // then: 예외 없이 여기까지 왔다 = 구조가 일치한다 (콘솔에 drop/create 가 새로 찍히지 않는 것도 확인)
        assertTrue(validateFactory.isOpen());
        validateFactory.close();
    }

    @Test
    public void validate_모드는_구조가_다르면_실패한다() {

        // given: 일부러 테이블에서 nickname 컬럼을 지워 엔티티(SchemaMember)와 구조를 다르게 만든다
        runSql("ALTER TABLE lab_schema_member DROP COLUMN nickname");

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "validate");

        boolean failed = false;
        try {
            // when: 구조 검사 → nickname 컬럼이 없으므로 EMF 생성 단계에서 예외
            EntityManagerFactory validateFactory = Persistence.createEntityManagerFactory("mappinglab", properties);
            validateFactory.close();
        } catch (RuntimeException e) {
            failed = true;
            System.out.println(">>> validate 실패! 원인:");
            SchemaPrinter.printCauses(e);   // 안쪽 원인에 "missing column [nickname]" 같은 메시지가 보인다
        } finally {
            // 다른 테스트가 nickname 을 쓰므로 원래대로 되돌린다
            runSql("ALTER TABLE lab_schema_member ADD COLUMN nickname varchar(255)");
        }

        // then
        assertTrue(failed);
    }

    /* 네이티브 SQL 로 DDL 실행. executeUpdate() 는 트랜잭션 안에서만 쓸 수 있다 */
    private void runSql(String sql) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createNativeQuery(sql).executeUpdate();
        transaction.commit();
    }
}

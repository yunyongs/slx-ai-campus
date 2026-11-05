package com.bear.section04.pk;

import com.bear.common.SchemaPrinter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 * [4. Primary Key 생성 전략]
 *
 * 실행: 클래스 이름 옆 ▶ (또는 ./gradlew test --tests "com.bear.section04.*")
 *  → 전략별로 비교하려면 테스트 메서드 하나씩 ▶ 로 실행하는 것을 추천 (콘솔이 섞이지 않는다)
 *
 * 모든 테스트가 같은 순서로 ">>>" 표시를 찍는다:
 *   [persist 전] 식별자 → [persist 직후] 식별자 → [commit 직후] 식별자
 * 그 사이에 Hibernate 가 찍는 SQL 의 "위치"를 보면, 전략마다 번호를 언제 어떻게 얻는지 알 수 있다.
 *
 *   전략       | 번호를 얻는 방법                       | INSERT 시점
 *   IDENTITY  | INSERT 해서 DB 가 만든 번호를 받음        | persist() 즉시
 *   SEQUENCE  | 시퀀스(MySQL 은 흉내 테이블)에서 먼저 받음 | commit (flush)
 *   TABLE     | lab_id_generator 행을 읽고 갱신         | commit (flush)
 *   UUID      | 자바에서 바로 생성 (SQL 없음)            | commit (flush)
 *   직접 할당  | 우리가 넣은 값                          | commit (flush)
 */
public class D_PrimaryKeyStrategyTest {

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
    public void IDENTITY_persist_순간_INSERT가_실행된다() {

        IdentityItem item = new IdentityItem("IDENTITY 상품");
        System.out.println(">>> [IDENTITY] persist 전 itemNo = " + item.getItemNo());

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(item);                  // ← 이 줄에서 insert into lab_identity_item ... 가 찍힌다
        System.out.println(">>> [IDENTITY] persist 직후 itemNo = " + item.getItemNo() + "  (INSERT 가 위에 이미 찍혀 있다)");

        transaction.commit();
        System.out.println(">>> [IDENTITY] commit 직후 itemNo = " + item.getItemNo());

        assertNotNull(item.getItemNo());
    }

    @Test
    public void SEQUENCE_번호를_먼저_받고_INSERT는_commit_때() {

        SequenceItem item = new SequenceItem("SEQUENCE 상품");
        System.out.println(">>> [SEQUENCE] persist 전 itemNo = " + item.getItemNo());

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(item);                  // ← 번호 받기 SQL(lab_seq_item_seq 조회·갱신)만 찍히고 INSERT 는 아직
        System.out.println(">>> [SEQUENCE] persist 직후 itemNo = " + item.getItemNo() + "  (번호는 있는데 INSERT 는 아직)");

        transaction.commit();                         // ← 여기서 insert into lab_sequence_item ...
        System.out.println(">>> [SEQUENCE] commit 직후 itemNo = " + item.getItemNo());

        assertNotNull(item.getItemNo());
    }

    @Test
    public void TABLE_식별자_테이블을_읽고_갱신한다() {

        TableItem first = new TableItem("TABLE 상품 1");
        TableItem second = new TableItem("TABLE 상품 2");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(first);                 // ← select ... from lab_id_generator / update lab_id_generator
        entityManager.persist(second);                // ← allocationSize = 1 이므로 또 한 번 select / update
        System.out.println(">>> [TABLE] persist 직후 itemNo = " + first.getItemNo() + ", " + second.getItemNo());

        transaction.commit();                         // ← INSERT 2개

        // 식별자 관리 테이블 안을 직접 보자: table_item 행의 next_val 이 얼마인지
        Object nextVal = entityManager.createNativeQuery(
                        "SELECT next_val FROM lab_id_generator WHERE gen_name = 'table_item'")
                .getSingleResult();
        System.out.println(">>> [TABLE] lab_id_generator 의 table_item 행 next_val = " + nextVal);

        // then: 두 번호는 1 차이 (allocationSize = 1)
        assertEquals(1L, second.getItemNo() - first.getItemNo());
    }

    @Test
    public void AUTO_MySQL이라도_IDENTITY가_아닐_수_있다() {

        AutoItem item = new AutoItem("AUTO 상품");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(item);
        System.out.println(">>> [AUTO] persist 직후 itemNo = " + item.getItemNo()
                + "  (위에 INSERT 가 없고 *_SEQ 테이블 조회만 있으면 → IDENTITY 가 아니라 시퀀스 흉내 방식)");

        transaction.commit();

        // lab_auto_item 의 item_no 컬럼에 auto_increment 가 붙었는지 표로 확인 (붙지 않았으면 IDENTITY 가 아니다)
        SchemaPrinter.printColumns(entityManager, "lab_auto_item");

        assertNotNull(item.getItemNo());
    }

    @Test
    public void UUID_자바에서_바로_만든다() {

        UuidItem item = new UuidItem("UUID 상품");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(item);                  // ← SQL 이 하나도 안 찍힌다
        System.out.println(">>> [UUID] persist 직후 itemId = " + item.getItemId() + "  (SQL 없이 생성됨)");

        transaction.commit();                         // ← INSERT

        SchemaPrinter.printColumns(entityManager, "lab_uuid_item");   // item_id 의 컬럼 타입 확인 (binary(16) 예상)

        assertNotNull(item.getItemId());
    }

    @Test
    public void 직접_할당_값을_안_넣으면_persist가_거부된다() {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // ① 값을 넣은 경우: 정상
        AssignedItem withId = new AssignedItem(100L, "직접 할당 상품");
        entityManager.persist(withId);
        System.out.println(">>> [직접 할당] itemNo = " + withId.getItemNo());

        // ② 값을 안 넣은 경우: 생성 전략이 없으니 누가 번호를 줄 수 없다 → 예외
        boolean failed = false;
        try {
            entityManager.persist(new AssignedItem(null, "번호 없는 상품"));
        } catch (RuntimeException e) {
            failed = true;
            System.out.println(">>> [직접 할당] 번호 없이 persist → 거부:");
            SchemaPrinter.printCauses(e);
        }

        if (transaction.isActive()) {
            transaction.rollback();   // 실습이므로 저장하지 않고 정리
        }

        assertTrue(failed);
    }
}

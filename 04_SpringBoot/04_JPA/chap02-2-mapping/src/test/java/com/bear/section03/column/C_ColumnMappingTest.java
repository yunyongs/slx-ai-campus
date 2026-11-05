package com.bear.section03.column;

import com.bear.common.SchemaPrinter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/*
 * [3. Column 과 기본 타입 매핑]
 *
 * 실행: 클래스 이름 옆 ▶ (또는 ./gradlew test --tests "com.bear.section03.*")
 *
 * 테스트마다 같은 패턴: 저장(commit) → clear() → 다시 조회
 *  clear() 를 하는 이유: 1차 캐시에 남은 "자바 객체" 가 아니라 "DB 에 실제로 들어간 값" 을 보기 위해서
 *
 * 테스트끼리 데이터가 겹치지 않도록 product_code 를 테스트마다 다르게 쓴다 (C-001, C-002 ...)
 */
public class C_ColumnMappingTest {

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
    public void Column_속성이_DDL에_어떻게_반영됐는지_본다() {

        int columnCount = SchemaPrinter.printColumns(entityManager, "lab_product");

        // @Transient 인 displayName 은 컬럼이 없다 → 필드 12개 중 11개만 컬럼
        assertEquals(11, columnCount);

        // 콘솔 표에서 확인할 것
        //  product_code : varchar(20), NULL 허용 NO, 키 UNI      ← nullable=false, length=20, unique=true
        //  price        : decimal(10,2)                          ← BigDecimal + precision/scale
        //  memo, note   : 기본값 미입력                           ← columnDefinition
        //  release_date : date / created_at : datetime(6)        ← LocalDate / LocalDateTime
        //  displayName  : 없음                                    ← @Transient
        // 콘솔 위쪽 DDL 에서 확인할 것: "unique (product_name, maker)" ← @Table 의 uniqueConstraints
    }

    @Test
    public void insertable_false면_INSERT에서_빠져서_DB_기본값이_들어간다() {

        // given
        LabProduct product = new LabProduct("C-001", "곰돌이 텀블러", "베어컴퍼니", new BigDecimal("12000.50"));
        product.setMemo("내가 넣은 메모");   // insertable = false → 무시된다
        product.setNote(null);              // insertable = true  → INSERT 에 null 이 그대로 들어간다

        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(product);     // 콘솔 INSERT 문의 컬럼 목록에 memo 가 없는 것을 확인!
        transaction.commit();

        System.out.println(">>> commit 직후 자바 객체의 memo = " + product.getMemo() + " (1차 캐시의 자바 값일 뿐)");
        entityManager.clear();

        LabProduct found = entityManager.find(LabProduct.class, product.getProductNo());
        System.out.println(">>> DB 에서 다시 읽은 값 = " + found);

        // then
        assertEquals("미입력", found.getMemo());   // INSERT 에서 빠졌으니 DB 기본값
        assertNull(found.getNote());               // INSERT 에 null 이 들어갔으니 기본값 적용 안 됨
    }

    @Test
    public void updatable_false면_UPDATE에서_빠진다() {

        // given: registeredBy = 관리자A 로 저장
        LabProduct product = new LabProduct("C-002", "곰돌이 머그컵", "베어컴퍼니", new BigDecimal("8000"));
        product.setRegisteredBy("관리자A");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();

        // when: 영속 상태에서 두 필드를 바꾸고 commit → 변경 감지가 UPDATE 를 만든다
        transaction.begin();
        product.setRegisteredBy("관리자B");                   // updatable = false
        product.setPrice(new BigDecimal("9000"));            // 보통 컬럼
        transaction.commit();                                 // 콘솔 UPDATE 문의 SET 목록에 registered_by 가 없다!

        entityManager.clear();
        LabProduct found = entityManager.find(LabProduct.class, product.getProductNo());
        System.out.println(">>> DB 에서 다시 읽은 값 = " + found);

        // then
        assertEquals("관리자A", found.getRegisteredBy());              // UPDATE 에서 빠졌으므로 처음 값 그대로
        assertEquals(0, new BigDecimal("9000").compareTo(found.getPrice()));   // 가격은 바뀜 (9000 vs 9000.00 이라 compareTo 로 비교)
    }

    @Test
    public void Transient_필드는_저장도_조회도_되지_않는다() {

        // given
        LabProduct product = new LabProduct("C-003", "곰돌이 스티커", "베어컴퍼니", new BigDecimal("1500"));
        product.setDisplayName("[한정판] 곰돌이 스티커");   // 화면 표시용 임시 값

        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(product);   // INSERT 컬럼 목록에 displayName 이 없다
        transaction.commit();
        entityManager.clear();

        LabProduct found = entityManager.find(LabProduct.class, product.getProductNo());

        // then
        System.out.println(">>> 저장 전 displayName = " + product.getDisplayName() + " / 다시 읽은 displayName = " + found.getDisplayName());
        assertNull(found.getDisplayName());
    }

    @Test
    public void 날짜_시간_타입_저장과_조회() {

        // given
        LabProduct product = new LabProduct("C-004", "곰돌이 달력", "베어컴퍼니", new BigDecimal("7000"));
        product.setReleaseDate(LocalDate.of(2026, 11, 5));
        product.setCreatedAt(LocalDateTime.of(2026, 11, 5, 14, 30, 0));
        product.setLegacyDate(new Date());

        // when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(product);
        transaction.commit();
        entityManager.clear();

        LabProduct found = entityManager.find(LabProduct.class, product.getProductNo());
        System.out.println(">>> releaseDate = " + found.getReleaseDate() + " / createdAt = " + found.getCreatedAt()
                + " / legacyDate = " + found.getLegacyDate());

        // then: java.time 타입은 @Temporal 없이 그대로 저장·복원된다
        assertEquals(LocalDate.of(2026, 11, 5), found.getReleaseDate());
        assertEquals(LocalDateTime.of(2026, 11, 5, 14, 30, 0), found.getCreatedAt());
    }

    @Test
    public void nullable_false_컬럼에_null을_넣으면_저장이_거부된다() {

        // given: product_name(nullable = false) 에 null
        LabProduct product = new LabProduct("C-005", null, "베어컴퍼니", new BigDecimal("1000"));

        EntityTransaction transaction = entityManager.getTransaction();
        boolean failed = false;

        // when
        try {
            transaction.begin();
            entityManager.persist(product);   // IDENTITY → INSERT 가 지금 실행되므로 여기서 막힐 가능성이 크다
            transaction.commit();
        } catch (RuntimeException e) {
            failed = true;
            System.out.println(">>> 저장 거부! 누가 막았는지 원인을 보자:");
            SchemaPrinter.printCauses(e);
            // Hibernate 가 먼저 막으면 "not-null property references a null ..." (PropertyValueException 계열)
            // DB 가 막으면 "Column 'product_name' cannot be null" (ConstraintViolationException 계열)
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        // then
        assertTrue(failed);
    }

    @Test
    public void unique_컬럼에_같은_값을_두_번_넣으면_거부된다() {

        // given: C-006 을 한 번 저장
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(new LabProduct("C-006", "곰돌이 키링", "베어컴퍼니", new BigDecimal("3000")));
        transaction.commit();

        // when: 같은 product_code 로 한 번 더
        boolean failed = false;
        try {
            transaction.begin();
            entityManager.persist(new LabProduct("C-006", "곰돌이 키링2", "베어컴퍼니", new BigDecimal("3500")));
            transaction.commit();
        } catch (RuntimeException e) {
            failed = true;
            System.out.println(">>> 중복 거부! 원인:");
            SchemaPrinter.printCauses(e);   // "Duplicate entry 'C-006' for key ..." 가 보인다
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

        // then
        assertTrue(failed);
    }
}

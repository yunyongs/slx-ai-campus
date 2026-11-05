package com.bear.section07.compositekey;

import com.bear.common.SchemaPrinter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*
 * [7. Composite Key]
 *
 * 실행: 클래스 이름 옆 ▶ (또는 ./gradlew test --tests "com.bear.section07.*")
 *
 * 흐름: 키 값을 직접 넣어 저장(commit) → clear() → "전체 키를 가진 키 객체"로 find()
 * 테스트끼리 겹치지 않도록 키 값을 테스트마다 다르게 쓴다.
 */
public class G_CompositeKeyTest {

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
    public void EmbeddedId_키_객체로_저장하고_조회한다() {

        // 기본 키가 두 컬럼인지 표로 확인 (cart_no, product_code 모두 키 = PRI)
        SchemaPrinter.printColumns(entityManager, "lab_cart_item");

        // given
        CartItem item = new CartItem(new CartItemId(1L, "P-001"), 3);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);    // 복합 키는 자동 생성이 아니라서 INSERT 는 commit 때
        transaction.commit();
        entityManager.clear();

        // when: 전체 키(1, "P-001")로 조회 / 일부만 맞는 키(1, "P-999")로 조회
        CartItem found = entityManager.find(CartItem.class, new CartItemId(1L, "P-001"));
        CartItem notFound = entityManager.find(CartItem.class, new CartItemId(1L, "P-999"));
        System.out.println(">>> (1, P-001) 조회 = " + found);
        System.out.println(">>> (1, P-999) 조회 = " + notFound);

        // then
        assertEquals(3, found.getQuantity());
        assertNull(notFound);   // 키의 "일부"만 같으면 다른 행
    }

    @Test
    public void IdClass_엔티티_필드로_키를_선언하고_키_클래스로_조회한다() {

        SchemaPrinter.printColumns(entityManager, "lab_book_like");

        // given: 회원 10 이 책 100 에 좋아요
        BookLike like = new BookLike(10L, 100L, LocalDateTime.of(2026, 11, 5, 15, 0));

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(like);
        transaction.commit();
        entityManager.clear();

        // when: 키 클래스(LikeKey)에 전체 키를 담아서 조회
        BookLike found = entityManager.find(BookLike.class, new LikeKey(10L, 100L));
        System.out.println(">>> (10, 100) 조회 = " + found);

        // then: 키 값이 엔티티 필드로 바로 보인다 (EmbeddedId 는 getId().getXxx())
        assertEquals(10L, found.getMemberNo());
        assertEquals(100L, found.getBookNo());
    }

    @Test
    public void 키_객체는_equals로_비교된다_1차_캐시() {

        // given
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(new CartItem(new CartItemId(3L, "P-300"), 1));
        transaction.commit();
        entityManager.clear();

        // 값은 같지만 서로 다른 키 객체 2개
        CartItemId key1 = new CartItemId(3L, "P-300");
        CartItemId key2 = new CartItemId(3L, "P-300");

        // when
        CartItem first = entityManager.find(CartItem.class, key1);    // SELECT 실행 → 1차 캐시에 저장
        CartItem second = entityManager.find(CartItem.class, key2);   // SQL 없음 → 1차 캐시에서 꺼냄 (콘솔에 select 가 1번만!)

        // then
        assertNotSame(key1, key2);   // 키 객체는 다르지만
        assertEquals(key1, key2);    // equals 로 보면 같은 키
        assertSame(first, second);   // 그래서 1차 캐시에서 같은 엔티티 객체가 나온다

        // 바꿔 보기: CartItemId 의 equals()/hashCode() 를 주석 처리하고 다시 실행
        //  → key1, key2 가 "다른 키"가 되어 두 번째 find 에서도 SELECT 가 나가고, assertSame 이 깨진다 (Hibernate 가 경고를 낼 수도 있다)
    }
}

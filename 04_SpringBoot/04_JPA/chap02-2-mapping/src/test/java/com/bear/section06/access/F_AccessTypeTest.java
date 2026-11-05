package com.bear.section06.access;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 * [6. Access: FIELD vs PROPERTY]
 *
 * 실행: 클래스 이름 옆 ▶ (또는 ./gradlew test --tests "com.bear.section06.*")
 *
 * 두 엔티티의 getNickname() 은 똑같이 뒤에 "님"을 붙인다. 차이는 JPA 가 그 getter 를 쓰느냐 마느냐.
 * 콘솔에서 "getNickname() 호출됨" 문장이 JPA 동작(persist/commit/find) 사이에 찍히는지 보자.
 */
public class F_AccessTypeTest {

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
    public void FIELD_접근은_getter를_거치지_않고_필드값을_저장한다() {

        FieldAccessMember member = new FieldAccessMember("곰");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println(">>> persist 시작 (아래에 getNickname() 호출 문장이 없어야 한다)");
        entityManager.persist(member);
        transaction.commit();

        String saved = rawNickname("lab_field_access_member", member.getMemberNo());
        System.out.println(">>> DB 에 저장된 nickname = " + saved);

        assertEquals("곰", saved);   // getter 의 "님" 이 안 붙었다
    }

    @Test
    public void PROPERTY_접근은_getter의_결과를_저장한다() {

        PropertyAccessMember member = new PropertyAccessMember("곰");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println(">>> persist 시작 (아래에 getNickname() 호출 문장이 찍힌다)");
        entityManager.persist(member);
        transaction.commit();

        String saved = rawNickname("lab_property_access_member", member.getMemberNo());
        System.out.println(">>> DB 에 저장된 nickname = " + saved);

        assertEquals("곰님", saved);   // JPA 가 getter 로 읽었으므로 가공된 값이 저장됐다
    }

    @Test
    public void PROPERTY_접근의_함정_조회만_했는데_UPDATE가_나간다() {

        // given: "호랑이" 저장 → DB 에는 getter 결과 "호랑이님"
        PropertyAccessMember member = new PropertyAccessMember("호랑이");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(member);
        transaction.commit();
        entityManager.clear();

        // when: 트랜잭션 안에서 "조회만" 하고 commit
        transaction.begin();
        System.out.println(">>> find 시작: DB 값 \"호랑이님\" 으로 setNickname(...) 이 호출된다");
        PropertyAccessMember found = entityManager.find(PropertyAccessMember.class, member.getMemberNo());
        System.out.println(">>> commit 시작: 변경 감지가 getNickname() 으로 현재 값을 읽는다 → \"호랑이님님\" ≠ 스냅샷 \"호랑이님\"");
        transaction.commit();   // ← 아무것도 안 바꿨는데 콘솔에 update 가 찍힌다!

        String saved = rawNickname("lab_property_access_member", found.getMemberNo());
        System.out.println(">>> DB 에 저장된 nickname = " + saved);

        // then: 가공 로직이 저장·조회를 거치며 계속 덧붙는다
        assertEquals("호랑이님님", saved);

        // 교훈: PROPERTY 접근을 쓸 때 getter 에 가공 로직을 넣지 말 것 (화면용 가공은 별도 메서드로)
    }

    /* JPA 를 거치지 않고 DB 에 실제로 들어 있는 nickname 을 읽는다 */
    private String rawNickname(String tableName, Long memberNo) {
        Object value = entityManager.createNativeQuery(
                        "SELECT nickname FROM " + tableName + " WHERE member_no = :memberNo")
                .setParameter("memberNo", memberNo)
                .getSingleResult();
        return String.valueOf(value);
    }
}

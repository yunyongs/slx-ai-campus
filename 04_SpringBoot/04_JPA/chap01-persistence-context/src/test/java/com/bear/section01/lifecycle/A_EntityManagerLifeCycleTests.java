package com.bear.section01.lifecycle;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;


public class A_EntityManagerLifeCycleTests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    /*
    * -전체 테스트가 실행되기 전 딱 한번만 실행됨
    * */
    @BeforeAll
    public static void initFactory() {
        /*
        * 1. persistence.xml: 설정을 읽어서 데이터베이스 연결 정보 및 엔티티 클래스 목록을 파악
        * 2. 메타 데이터 처리: 엔티티 클래스 구조 & 관계 정보 저장
        * 3. 커넥션 풀 초기화: 데이터베이스 연결을 위해 내부 커넥션 풀을 설정 및 초기화
        * 4. 캐시 초기화: 성능 최적화를 위한 (1차 캐시, 쿼리 캐시 초기화)
        * */
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    // 테스트 메서드가 각각이 호출될 때 이전에 동작
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

    /*
    *  테스트 코드는 다음과 같이 나누어 작성
    * 1. given: 테스트를 진행하기 위해 준비작업(전제조건) 수행
    * 2. when: 테스트의 핵심로직(행동 또는 메서드) 수행
    * 3. then: 테스트의 결과를 검증
    * */
    @Test
    public void 테스트_메소드() {

        // given
        TestClass testClass = new TestClass();
        String str1 = "첫번째 문자";
        String str2 = "두번째 문자";

        // when
        String result = testClass.sampleTest(str1, str2);

        // then
        Assertions.assertNotNull(result);

    }

    @Test
    public void 엔티티매니저_팩토리_생성_확인1() {
        System.out.println("entityManagerFactory = " + entityManagerFactory.hashCode());
        System.out.println("entityManager = " + entityManager.hashCode());
    }

    @Test
    public void 엔티티매니저_팩토리_생성_확인2() {
        System.out.println("entityManagerFactory = " + entityManagerFactory.hashCode());
        System.out.println("entityManager = " + entityManager.hashCode());
    }
}

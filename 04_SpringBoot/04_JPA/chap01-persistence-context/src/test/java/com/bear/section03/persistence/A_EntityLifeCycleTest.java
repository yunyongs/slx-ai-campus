package com.bear.section03.persistence;

import com.bear.section02.crud.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class A_EntityLifeCycleTest {

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
    public void 준영속성_detach_테스트() {

        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 12);

        // 준영속 상태: 컨텍스트로 관리하면 엔티티 객체를 관리하지 않는 상태가 되면 준영속 상태라고함
        entityManager.detach(foundMenu2); // foundMenu2를 준영속 상태로 변경

        foundMenu1.setMenuPrice(5000); // 영속 상태에서의 변경

        foundMenu2.setMenuPrice(5000); // 준영속 상태에서의 변경

        //영속성 컨텍스트에서 변경된 내용을 가지고 옴
        assertEquals(5000, entityManager.find(Menu.class, 11).getMenuPrice());

        //영속 상태가 아니었기 때문에 2000으로 나옴. false
        assertEquals(5000, entityManager.find(Menu.class, 12).getMenuPrice());
    }

    @Test
    public void 준영속성_clear_테스트() {

        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 12);

        entityManager.clear(); //둘 다 준 영속상태로 만듦 (지속성-persistence를 클리어-db와의 연결을 끊음: *내 생각)

        foundMenu1.setMenuPrice(5000);

        foundMenu2.setMenuPrice(5000);

        assertEquals(5000, entityManager.find(Menu.class, 11).getMenuPrice());

        assertEquals(5000, entityManager.find(Menu.class, 12).getMenuPrice());
    }

    @Test
    public void close_테스트() {

        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 12);

        // 영속성 컨텍스트를 닫는다. (재사용 안 됨)
        entityManager.close();

        foundMenu1.setMenuPrice(5000); //닫으면 클래스가 그냥 자바 객체가 됨. 그래서 set은 가능

        foundMenu2.setMenuPrice(5000);

        //그러나 그냥 자바 객체라서 디비에서 찾아올 수 없음. (*내 생각)
        assertEquals(5000, entityManager.find(Menu.class, 11).getMenuPrice());

        assertEquals(5000, entityManager.find(Menu.class, 12).getMenuPrice());
    }


    /*
    * merge() : 준영속 엔티티 객체의 식별자 값으로 1차 엔티티 객체를 조회
    * 조회한 엔티티 객체에 준영속 상태의 엔티티 객체의 값을 병합해서 엔티티 객체를 반환
    * -> 영속성 컨텍스트에 관리를 받지 않는 엔티티 값을 영속성 컨텍스트에 관리되는 엔티티에 복사하는 기능
    * */
    @Test
    public void 병합_merge_수정_테스트() {

        Menu menuToDetach = entityManager.find(Menu.class, 2); // 영속상태
        entityManager.detach(menuToDetach); // 준영속 (영속 X)

        menuToDetach.setMenuName("수박죽"); // 바꿔줬을때 -> 반영 안됨!

        Menu refoundMenu = entityManager.find(Menu.class, 2); // 영속상태

        System.out.println(menuToDetach.hashCode());
        System.out.println(refoundMenu.hashCode());

        entityManager.merge(menuToDetach); // 준 영속 객체의 값을 영속객체로 옮기는 행위

        // 캐싱된 영속 객체가 반환됨
        Menu mergedMenu = entityManager.find(Menu.class, 2);
        assertEquals("수박죽", mergedMenu.getMenuName());

        // @Transactional을 설정하지 않았기 때문에 실제 DB 변경은 일어나지 않음
    }

    @Test
    public void 병합_merge_삽입_테스트() {

        Menu menuToDetach = entityManager.find(Menu.class, 2); // 영속상태
        entityManager.detach(menuToDetach); // 준영속 상태

        menuToDetach.setMenuCode(999); //데이터 변경(ID)
        menuToDetach.setMenuName("수박죽"); //데이터 변경

        //merge는 있으면 병합하고 없으면 새로 만듦
        entityManager.merge(menuToDetach);

        Menu mergedMenu = entityManager.find(Menu.class, 999);
        assertEquals("수박죽", mergedMenu.getMenuName());

    }
}

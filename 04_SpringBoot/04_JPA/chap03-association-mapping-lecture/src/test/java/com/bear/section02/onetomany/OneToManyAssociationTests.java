package com.bear.section02.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OneToManyAssociationTests {
	
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
	public void 일대다_연관관계_객체_그래프_탐색을_이용한_조회_테스트() {
		
		// given
		int categoryCode = 10;

		/*
		* LAZY
		* - 객체가 실제 필요한 시점에만 데이터를 로드하기 때문에 시스템 리소스를 효율적으로 사용 가능하다
		* */
		// when
		System.out.println("find() 호출하기 일보 직전!");
		CategoryAndMenu categoryAndMenu = entityManager.find(CategoryAndMenu.class, categoryCode);
		System.out.println("find() 호출 완료");

		// then
		assertNotNull(categoryAndMenu);

		System.out.println("categoryAndMenu 인스턴스에 최초로 접근하기 일보 직전!");
		System.out.println(categoryAndMenu);
		System.out.println("categoryAndMenu 인스턴스에 최초로 접근 완료.");
	}
	
	@Test
	public void 일대다_연관관계_객체_삽입_테스트() {
		
		// given
		CategoryAndMenu categoryAndMenu = new CategoryAndMenu();

		categoryAndMenu.setCategoryCode(888);
		categoryAndMenu.setCategoryName("일대다추가카테고리");
		categoryAndMenu.setRefCategoryCode(null);
		
		List<Menu> menuList = new ArrayList<>();
		Menu menu = new Menu();

		menu.setMenuCode(777);
		menu.setMenuName("일대다아이스크림");
		menu.setMenuPrice(50000);
		menu.setOrderableStatus("Y");

		//부모 키가 있어야 자식 테이블에 데이터 입력 가능
		menu.setCategoryCode(categoryAndMenu.getCategoryCode());
		
		menuList.add(menu);
		
		categoryAndMenu.setMenuList(menuList);
		
		// when
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(categoryAndMenu);

		/*
		* 테이블 관계에서 외래키는 다(N)인 쪽인 tbl_menu가 들고 있는데,
		* 객체 관계에서는 CategoryAndMenu가 외래키를 관리하고 있기 때문에
		* Menu를 Insert한 뒤에 연관관계에 외래키를 반영하기 위해 update가 추가 될 수 있다.
		*
		* */
		System.out.println("commit() 호출 직전");
		entityTransaction.commit();
		System.out.println("commit() 호출 완료");

		CategoryAndMenu foundCategoryAndMenu = entityManager.find(CategoryAndMenu.class, 888);

		System.out.println(foundCategoryAndMenu);
	}

}

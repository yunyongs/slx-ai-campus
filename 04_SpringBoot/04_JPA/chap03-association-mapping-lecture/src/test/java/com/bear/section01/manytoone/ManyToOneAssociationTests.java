package com.bear.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManyToOneAssociationTests {

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


	/*
	* 연관관계: 엔티티 클래스 간의 관계를 매핑하는 것
	* 1. 다중성에 의한 분류
	* - N:1(ManyToOne) (이게 일반)
	* - 1:N(oneToMany)
	* - 1:1
	* - N:M
	* 2. 방향에 따른 분류
	* - 단방향 연관관계 (이게 일반)
	* - 양방향 연관관계
	*
	* */
	@Test
	public void 다대일_연관관계_객체_그래프_탐색을_이용한_조회_테스트() {

		// given
		int menuCode = 15;

		// when
		MenuAndCategory foundMenu = entityManager.find(MenuAndCategory.class, menuCode);
		Category menuCategory = foundMenu.getCategory();

		// then
		assertNotNull(menuCategory);
		System.out.println("foundMenu = " + foundMenu);
		System.out.println("menuCategory = " + menuCategory);
	}

	@Test
	public void 다대일_연관관계_객체지향쿼리_사용한_카테고리_이름_조회_테스트() {

		// given
		String jpql = "SELECT c.categoryName FROM menu_and_category m JOIN m.category c WHERE m.menuCode = 15";


		// when
		String category = entityManager.createQuery(jpql, String.class).getSingleResult();

		// then
		assertNotNull(category);
		System.out.println("category = " + category);
	}


	@Test
	public void 다대일_연관관계_객체_삽입_테스트() {

		// given
		MenuAndCategory menuAndCategory = new MenuAndCategory();
		menuAndCategory.setMenuCode(99999);
		menuAndCategory.setMenuName("죽방멸치빙수");
		menuAndCategory.setMenuPrice(30000);

		Category category = new Category();
		category.setCategoryCode(33333);
		category.setCategoryName("신규카테고리");
		category.setRefCategoryCode(null);

		menuAndCategory.setCategory(category);
		menuAndCategory.setOrderableStatus("Y");

		// when
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(menuAndCategory);

		entityTransaction.commit();

		/*
		* persist()에 전달한 entity는 menuAndCategory하나 뿐인데, category까지 함께 저장됨
		* - MenuCategory의 ManyToOne에 Cascade.Type.Persist로 영속성 전파를 해 놨기 때문
		*  -> 안해주면 비영속 객체 관련 예외 발생!
		* */

		// then
		MenuAndCategory foundMenuAndCategory = entityManager.find(MenuAndCategory.class, 99999);

		assertEquals(99999, foundMenuAndCategory.getMenuCode());
		assertEquals(33333, foundMenuAndCategory.getCategory().getCategoryCode());

	}

}








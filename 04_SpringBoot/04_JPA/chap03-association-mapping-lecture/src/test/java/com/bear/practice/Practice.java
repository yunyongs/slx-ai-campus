package com.bear.practice;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Practice {

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

    //@Disabled
    @Test
    public void 문제01_메뉴에서_연관된_카테고리를_조회할_수_있는지_예측한다() {
        PracticeMenu foundMenu = entityManager.find(PracticeMenu.class, 15);

        PracticeCategory foundCategory = foundMenu.getCategory();
        System.out.println("foundCategory = " + foundCategory);
        assertNotNull(foundCategory);
    }

    //@Disabled
    @Test
    public void 문제02_다대일_연관_객체가_지연_로딩되는지_예측한다() {
        PersistenceUnitUtil persistenceUnitUtil = entityManagerFactory.getPersistenceUnitUtil();
        PracticeMenu foundMenu = entityManager.find(PracticeMenu.class, 15);

        boolean categoryLoaded = persistenceUnitUtil.isLoaded(foundMenu, "category");

        assertFalse(categoryLoaded);
    }

    //@Disabled
    @Test
    public void 문제03_일대다_목록이_접근하기_전에_로딩되는지_예측한다() {
        PersistenceUnitUtil persistenceUnitUtil = entityManagerFactory.getPersistenceUnitUtil();
        PracticeCategory foundCategory = entityManager.find(PracticeCategory.class, 10);

        boolean menuListLoaded = persistenceUnitUtil.isLoaded(foundCategory, "menuList");

        assertFalse(menuListLoaded);
    }

    //@Disabled
    @Test
    public void 문제04_주인이_아닌_쪽의_목록만_변경하면_외래키가_변경되는지_예측한다() {
        PracticeCategory category = entityManager.find(PracticeCategory.class, 10);
        PracticeMenu menu = entityManager.find(PracticeMenu.class, 15);

        category.getMenuList().add(menu);
        entityManager.clear();

        PracticeMenu foundMenu = entityManager.find(PracticeMenu.class, 15);
        assertEquals(10, foundMenu.getCategory().getCategoryCode());
    }

    //@Disabled
    @Test
    public void 문제05_연관관계의_주인을_변경하면_외래키가_변경되는지_예측한다() {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        PracticeCategory category = entityManager.find(PracticeCategory.class, 10);
        PracticeMenu menu = entityManager.find(PracticeMenu.class, 15);

        menu.setCategory(category);
        entityManager.flush();
        entityManager.clear();

        PracticeMenu foundMenu = entityManager.find(PracticeMenu.class, 15);
        assertEquals(10, foundMenu.getCategory().getCategoryCode());

        entityTransaction.rollback();
    }

}

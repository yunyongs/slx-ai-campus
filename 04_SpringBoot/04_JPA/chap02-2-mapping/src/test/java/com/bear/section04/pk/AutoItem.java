package com.bear.section04.pk;

import jakarta.persistence.*;

/*
 * [4-3-4] AUTO: 생성 방식 선택을 구현체(Hibernate)에 맡긴다.
 *
 * "MySQL 이니까 IDENTITY 겠지?" → 아니다!
 *  Hibernate 는 숫자 식별자 + AUTO 를 "시퀀스 방식"으로 처리하고,
 *  MySQL 은 시퀀스가 없으니 테이블로 흉내 낸다 → 콘솔 DDL 에 AutoItem_SEQ 같은 테이블이 생기는지 확인.
 *  (테이블 이름은 Hibernate 버전에 따라 다를 수 있다)
 *
 * 그래서 MySQL 에서 AUTO_INCREMENT 를 쓰고 싶으면 IDENTITY 를 명시하는 것이 보통이다.
 */
@Entity(name = "AutoItem")
@Table(name = "lab_auto_item")
public class AutoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "item_name")
    private String itemName;

    public AutoItem() {}

    public AutoItem(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemNo() { return itemNo; }
    public String getItemName() { return itemName; }
}

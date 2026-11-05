package com.bear.section04.pk;

import jakarta.persistence.*;

/*
 * [4-3-1] IDENTITY: 기본 키 생성을 DB 에 위임 (MySQL AUTO_INCREMENT)
 *  - 번호는 INSERT 가 실행돼야 생긴다 → persist() 순간 INSERT 가 바로 나간다.
 *  - 그래도 저장 "확정"은 commit 에서.
 */
@Entity(name = "IdentityItem")
@Table(name = "lab_identity_item")
public class IdentityItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "item_name")
    private String itemName;

    public IdentityItem() {}

    public IdentityItem(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemNo() { return itemNo; }
    public String getItemName() { return itemName; }
}

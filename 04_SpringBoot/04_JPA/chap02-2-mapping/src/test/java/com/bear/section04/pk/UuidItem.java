package com.bear.section04.pk;

import jakarta.persistence.*;

import java.util.UUID;

/*
 * [4-3] UUID: 전 세계에서 겹치지 않을 128비트 값을 "자바 쪽에서" 만든다.
 *  - DB 에 물어볼 필요가 없으므로 persist() 때 SQL 없이 식별자가 정해진다 → INSERT 는 commit 때.
 *  - MySQL 에서는 보통 binary(16) 컬럼이 된다 (콘솔 DDL 확인).
 *  - 식별자 타입은 Long 이 아니라 java.util.UUID.
 */
@Entity(name = "UuidItem")
@Table(name = "lab_uuid_item")
public class UuidItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_id")
    private UUID itemId;

    @Column(name = "item_name")
    private String itemName;

    public UuidItem() {}

    public UuidItem(String itemName) {
        this.itemName = itemName;
    }

    public UUID getItemId() { return itemId; }
    public String getItemName() { return itemName; }
}

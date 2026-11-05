package com.bear.section04.pk;

import jakarta.persistence.*;

/*
 * [4-1] @Id 만 있고 @GeneratedValue 가 없다 → "직접 할당"
 *  - 식별자 값은 애플리케이션(우리 코드)이 persist() 전에 넣어야 한다.
 *  - 넣지 않고 persist() 하면 예외.
 */
@Entity(name = "AssignedItem")
@Table(name = "lab_assigned_item")
public class AssignedItem {

    @Id
    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "item_name")
    private String itemName;

    public AssignedItem() {}

    public AssignedItem(Long itemNo, String itemName) {
        this.itemNo = itemNo;
        this.itemName = itemName;
    }

    public Long getItemNo() { return itemNo; }
    public String getItemName() { return itemName; }
}

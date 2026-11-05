package com.bear.section04.pk;

import jakarta.persistence.*;

/*
 * [4-3-3, 4-4] TABLE: 식별자 관리용 테이블(lab_id_generator)로 번호를 만든다.
 *
 * lab_id_generator 테이블 모양 (콘솔 DDL 확인)
 *   gen_name (pkColumnName)   | next_val (valueColumnName)
 *   'table_item' (pkColumnValue) | 다음에 쓸 번호
 *
 * 번호를 받을 때마다: 이 행을 SELECT (잠금) → next_val 을 UPDATE → 받은 번호로 엔티티 식별자 설정
 *  → 콘솔에서 persist() 때 select ... from lab_id_generator / update lab_id_generator 가 찍힌다.
 *
 * initialValue, allocationSize 는 @GeneratedValue 가 아니라 이 생성기(@TableGenerator)의 속성이다.
 */
@Entity(name = "TableItem")
@Table(name = "lab_table_item")
@TableGenerator(
        name = "labTableGenerator",        // 생성기 이름 (@GeneratedValue 의 generator 와 같아야 함)
        table = "lab_id_generator",        // 식별자 관리용 테이블
        pkColumnName = "gen_name",         // 생성기를 구분하는 컬럼
        valueColumnName = "next_val",      // 번호를 담는 컬럼
        pkColumnValue = "table_item",      // 이 엔티티용 행의 이름
        allocationSize = 1                 // 1개씩 확보 (50 이면 한 번에 50개를 확보해 두고 메모리에서 나눠 준다)
)
public class TableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "labTableGenerator")
    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "item_name")
    private String itemName;

    public TableItem() {}

    public TableItem(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemNo() { return itemNo; }
    public String getItemName() { return itemName; }
}

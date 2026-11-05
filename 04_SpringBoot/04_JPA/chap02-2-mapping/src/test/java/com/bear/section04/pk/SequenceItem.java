package com.bear.section04.pk;

import jakarta.persistence.*;

/*
 * [4-3-2] SEQUENCE: DB 의 시퀀스 객체로 번호를 만든다 → INSERT 전에 번호를 먼저 받아 온다.
 *
 * 그런데 MySQL 에는 시퀀스 객체가 없다!
 *  → Hibernate 는 시퀀스를 "테이블 하나(lab_seq_item_seq)"로 흉내 낸다.
 *  → 콘솔 DDL 에서 "create sequence" 대신 "create table lab_seq_item_seq (next_val ...)" 가 보이는지 확인.
 *
 * @SequenceGenerator : 생성기 정의 (name = 생성기 이름, sequenceName = DB 시퀀스 이름, allocationSize = 한 번에 확보할 개수)
 * @GeneratedValue(generator = "labSeqGenerator") : 테이블 이름이 아니라 "생성기 이름" 으로 연결
 */
@Entity(name = "SequenceItem")
@Table(name = "lab_sequence_item")
@SequenceGenerator(name = "labSeqGenerator", sequenceName = "lab_seq_item_seq", allocationSize = 1)
public class SequenceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "labSeqGenerator")
    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "item_name")
    private String itemName;

    public SequenceItem() {}

    public SequenceItem(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemNo() { return itemNo; }
    public String getItemName() { return itemName; }
}

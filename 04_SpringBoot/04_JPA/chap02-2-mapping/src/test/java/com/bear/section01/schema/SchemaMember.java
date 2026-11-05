package com.bear.section01.schema;

import jakarta.persistence.*;

/*
 * [1. 스키마 자동 생성] 실습용 엔티티
 *
 * 흐름
 *  ① persistence.xml 의 hibernate.hbm2ddl.auto = create
 *  ② Persistence.createEntityManagerFactory("mappinglab") 를 부르는 순간
 *  ③ Hibernate 가 이 클래스의 매핑 정보(@Table, @Column ...)를 읽고
 *  ④ "drop table if exists lab_schema_member" → "create table lab_schema_member (...)" 를 실행한다.
 *
 * 콘솔에서 볼 것: member_id 가 varchar(30) not null 로 만들어지는지 (← 아래 @Column 의 length, nullable)
 */
@Entity(name = "SchemaMember")
@Table(name = "lab_schema_member")
public class SchemaMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // MySQL AUTO_INCREMENT
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "member_id", nullable = false, length = 30)
    private String memberId;

    @Column(name = "nickname")                            // length 생략 → 기본 255 → varchar(255)
    private String nickname;

    public SchemaMember() {}                               // JPA 는 기본 생성자가 필요하다 (2-2)

    public SchemaMember(String memberId, String nickname) {
        this.memberId = memberId;
        this.nickname = nickname;
    }

    public Long getMemberNo() { return memberNo; }
    public String getMemberId() { return memberId; }
    public String getNickname() { return nickname; }
}

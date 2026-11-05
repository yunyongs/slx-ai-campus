package com.bear.section02.entity;

import jakarta.persistence.*;

/*
 * [2. Entity 와 Table] 실습용 엔티티
 *
 * 이름이 두 개다
 *  - 엔티티 이름 "LabMember"  (@Entity 의 name) → JPQL 에서 쓴다:  SELECT m FROM LabMember m
 *  - 테이블 이름 "lab_member" (@Table 의 name)  → SQL 에서 쓴다:   SELECT * FROM lab_member
 *
 * 엔티티 클래스의 조건 (2-2) — 바꿔 보기 실험은 B_EntityAndTableTest 맨 아래 주석 참고
 *  - public 또는 protected 기본 생성자 필요 (아래에 직접 만들어 둠)
 *  - 클래스·영속 필드에 final 금지
 *  - enum / interface / record 는 엔티티가 될 수 없다
 */
@Entity(name = "LabMember")
@Table(name = "lab_member")
public class LabMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "member_id", nullable = false, length = 30)
    private String memberId;

    @Column(name = "nickname", length = 30)
    private String nickname;

    /*
     * 기본 생성자: 우리 코드는 아래 생성자를 쓰지만,
     * Hibernate 는 find()/JPQL 로 조회한 행을 객체로 만들 때 "이 생성자"로 빈 객체를 만든 뒤 필드를 채운다.
     * → 테스트 콘솔에 아래 문장이 언제 찍히는지 보자.
     */
    public LabMember() {
        System.out.println("    (LabMember 기본 생성자 호출 — Hibernate 가 조회 결과로 객체를 만드는 중)");
    }

    public LabMember(String memberId, String nickname) {
        this.memberId = memberId;
        this.nickname = nickname;
    }

    public Long getMemberNo() { return memberNo; }
    public String getMemberId() { return memberId; }
    public String getNickname() { return nickname; }

    @Override
    public String toString() {
        return "LabMember{memberNo=" + memberNo + ", memberId='" + memberId + "', nickname='" + nickname + "'}";
    }
}

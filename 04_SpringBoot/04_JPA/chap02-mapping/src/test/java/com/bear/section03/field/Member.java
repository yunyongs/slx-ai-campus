package com.bear.section03.field;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/*
* Entity : 데이터 테이블과 매핑되는 자바 클래스
* */
@Entity(name="member_section03")
@Table(name="tbl_member_section03") // 매핑될 테이블 작성
@Setter
@Access(AccessType.FIELD) // JPA 가 엔티티의 값을 필드를 통해 접근한다 (기본값 FIELD)
//@Access(AccessType.PROPERTY) // JPA 가 엔티티의 값을 게터/세터를 통해 접근한다 (기본값 FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @Column(name="member_no")
    /*
    * SEQUENCE : DB에 있는 시퀀스 객체 (Oracle, PostgreSQL)
    * Table : 키생성 전용 테이블 -> 기본 키값을 관리
    * AUTO : DB에 맞는 전략을 자동으로 선택
    * */
    private int memberNo;

    @Column(name="member_id")
    private String memberId;

    @Column(name="member_pwd")
    private String memberPwd;

    @Column(name="nickname")
    private String nickname;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="enroll_date")
    private Date enrollDate;

    @Column(name="member_role")
    private String memberRole;

    @Column(name="status")
    private String status;


    // getter 동작할때
    public String getMemberId() {
        System.out.println("getter에서 동작함...");
        return  memberId;
    }

    public String getMemberPwd() {
        System.out.println("getter에서 동작함...");
        return  memberPwd;
    }

    public String getNickname() {

        System.out.println("getter에서 동작함...");
        return  nickname;
    }
}

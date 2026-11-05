package com.bear.section01.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
/*
* Entity : 데이터 테이블과 매핑되는 자바 클래스
* */
@Entity(name="member_section01")
@Table(name="tbl_member_section01") // 매핑될 테이블 작성
@Getter
@Setter //엔티티에서는 삭제
@NoArgsConstructor //이거 필요
@AllArgsConstructor //엔티티에서는 삭제
@ToString
public class Member {

    @Id
    @Column(name="member_no")
    /*
    * SEQUENCE : DB에 있는 시퀀스 객체 (Oracle, PostgreSQL)
    * Table : 키생성 전용 테이블 -> 기본 키값을 관리
    * AUTO : DB에 맞는 전략을 자동으로 선택
    * */
    // SEQUENCE 전략은 오라클, PostgreSQL에서만 사용 가능, MySQL에서는 IDENTITY 전략을 사용해야 함
    @GeneratedValue(strategy = GenerationType.TABLE)
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


}

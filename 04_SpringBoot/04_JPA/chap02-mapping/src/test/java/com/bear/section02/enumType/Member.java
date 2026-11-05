package com.bear.section02.enumType;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/*
* Entity : 데이터 테이블과 매핑되는 자바 클래스
* */
@Entity(name="member_section02")
@Table(name="tbl_member_section02") // 매핑될 테이블 작성
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id
    @Column(name="member_no")
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
//    @Enumerated(EnumType.ORDINAL) // enum에 선언된 '순서' 1이 저장됨 (ADMIN=0 / MEMBER=1)
    @Enumerated(EnumType.STRING)
    private RoleType memberRole;

    @Column(name="status")
    private String status;


}

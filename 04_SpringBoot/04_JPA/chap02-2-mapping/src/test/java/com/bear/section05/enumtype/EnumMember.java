package com.bear.section05.enumtype;

import jakarta.persistence.*;

/*
 * [5-2] @Enumerated: Enum 을 DB 에 어떤 모양으로 저장할지
 *
 * 같은 RoleType 값을 세 컬럼에 다르게 저장해서 비교한다
 *  role_default : @Enumerated 생략      → 기본값 ORDINAL → 숫자
 *  role_ordinal : EnumType.ORDINAL      → 숫자 (선언 순서)
 *  role_string  : EnumType.STRING       → 상수 이름 글자 (MySQL 에서는 enum('ADMIN','MEMBER') 타입이 될 수 있다)
 */
@Entity(name = "EnumMember")
@Table(name = "lab_enum_member")
public class EnumMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "role_default")
    private RoleType roleDefault;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_ordinal")
    private RoleType roleOrdinal;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_string")
    private RoleType roleString;

    public EnumMember() {}

    // 실습을 위해 세 컬럼에 같은 값을 넣는다
    public EnumMember(RoleType role) {
        this.roleDefault = role;
        this.roleOrdinal = role;
        this.roleString = role;
    }

    public Long getMemberNo() { return memberNo; }
    public RoleType getRoleDefault() { return roleDefault; }
    public RoleType getRoleOrdinal() { return roleOrdinal; }
    public RoleType getRoleString() { return roleString; }
}

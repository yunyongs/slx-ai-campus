package com.bear.practice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "practice_enum_member")
@Table(name = "tbl_practice_enum_member")
@Getter
@Setter
public class EnumMember {

    @Id
    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private RoleType memberRole;
}

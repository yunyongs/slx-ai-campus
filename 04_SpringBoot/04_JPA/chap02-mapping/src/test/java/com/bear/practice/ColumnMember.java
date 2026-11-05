package com.bear.practice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "practice_column_member")
@Table(name = "tbl_practice_column_member")
@Getter
@Setter
public class ColumnMember {

    @Id
    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Transient
    private String nickname;

    @Column(name = "address", nullable = false)
    private String address;
}

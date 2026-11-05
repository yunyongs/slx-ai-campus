package com.bear.practice;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "practice_property_access_member")
@Table(name = "tbl_practice_property_access_member")
@Access(AccessType.PROPERTY)
public class PropertyAccessMember {

    private int memberNo;

    @Id
    @Column(name = "member_no")
    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }
}

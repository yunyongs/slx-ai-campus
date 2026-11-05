package com.bear.practice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "practice_entity_name_member")
@Table(name = "tbl_practice_entity_name_member")
public class EntityNameMember {

    @Id
    private int memberNo;
}

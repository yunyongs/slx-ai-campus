package com.bear.practice;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "practice_category")
@Table(name = "tbl_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PracticeCategory {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<PracticeMenu> menuList = new ArrayList<>();
}

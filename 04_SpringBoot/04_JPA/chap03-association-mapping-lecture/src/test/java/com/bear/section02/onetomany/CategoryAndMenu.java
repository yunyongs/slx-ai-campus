package com.bear.section02.onetomany;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "category_and_menu")
@Table(name = "tbl_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryAndMenu {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @JoinColumn(name = "category_code")
    @OneToMany(cascade = CascadeType.PERSIST) //특히 oneToMany에서의 조회전략 기본값은 LAZY임
    private List<Menu> menuList;

}

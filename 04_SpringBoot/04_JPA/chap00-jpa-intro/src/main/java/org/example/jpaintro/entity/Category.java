package org.example.jpaintro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "category")
@Table(name = "tbl_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

}

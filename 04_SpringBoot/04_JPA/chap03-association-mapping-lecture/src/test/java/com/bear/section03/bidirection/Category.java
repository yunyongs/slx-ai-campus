package com.bear.section03.bidirection;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity(name = "bidirection_category")
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

    /*
    * 양방향 매핑에서는 연관관계의 주인이 되려면 주인이 아닌 쪽에서는 속성을 지정해야 함
    * - 주인이 아닌 객체에 mappedBy를 써서 주인 객체의 필드명을 매핑시키면 양방향 적용
    *
    * */
	@OneToMany(mappedBy="category")
	@ToString.Exclude //: ToString에서 제외하겠다는 lombok 이노테이션
    private List<Menu> menuList;
}

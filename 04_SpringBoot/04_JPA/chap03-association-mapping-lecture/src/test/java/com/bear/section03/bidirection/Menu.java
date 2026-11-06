package com.bear.section03.bidirection;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "bidirection_menu")
@Table(name = "tbl_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

	@JoinColumn(name="category_code")
	@ManyToOne
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

}

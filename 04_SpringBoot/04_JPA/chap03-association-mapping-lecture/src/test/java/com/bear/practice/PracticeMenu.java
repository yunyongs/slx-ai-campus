package com.bear.practice;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "practice_menu")
@Table(name = "tbl_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PracticeMenu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @ManyToOne
//    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_code")
    private PracticeCategory category;

    @Column(name = "orderable_status")
    private String orderableStatus;
}

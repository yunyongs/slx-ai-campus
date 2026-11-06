package org.example.jpaintro.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

@Entity // 테이블의 한 행 - 하나의 레코드라고 볼 수 있다.
@Table(name = "tbl_menu")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Menu {

    @Id //식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment로 만듦
    @Column(name = "menu_code") //일반적으로 컬럼 지정함. 명시하기 위해서
    private Integer menuCode;

    @Column(name = "menu_name")
    private String menuName;


    @Column(name = "menu_price")
    private Integer menuPrice;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "category_code")
    private Category categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    public Menu(String menuName, Integer menuPrice, Category categoryCode, String orderableStatus) {
        change(menuName, menuPrice, categoryCode, orderableStatus);
    }

    //엔티티는 수정하면 안 되기 때문에 setter를 두지 않는다. 대신 change 메서드를 두어 모든 필드 값이 한꺼번에 바뀌도록 한다
    public void change(String menuName, Integer menuPrice, Category categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }
}

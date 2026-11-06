package com.bear.section01.manytoone;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuAndCategory {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    // @JoinColumn 이 없으면 외래키 컬럼 이름을 "필드명_참조PK" = category_category_code 로 추측한다
    // → tbl_menu 에는 그런 컬럼이 없어서 조회 SQL 이 실패 (Unknown column). 실제 컬럼 이름을 직접 지정
//    @JoinColumn(name = "category_code")
    @JoinColumn(
            name = "category_code",  // 외래키 컬럼 이름
            nullable = true,         // 외래키 컬럼에 NULL 을 허용할지
            unique = false,          // 외래키 컬럼에 유일성 제약을 둘지
            insertable = true,       // 외래키 컬럼은 SQL Insert에 포함할지
            updatable = true         // 외래키 컬럼은 SQL Update에 포함할지
    )
    /*
    * 매핑 어노테이션 설정
    * - fetch: 연관 엔티티의 조회 전략 지정 (Eager, Lazy)
    * - optional : 연관 엔티티가 없어도 되는지 지정 (default true)
    * - cascade : 현재 엔티티의 영속성 작업을 연관 엔티티에도 전파할지 설정
    *   - PERSIST : 현재 엔티티를 저장할때 연관 엔티티도 같이 저장
    *   - MERGE : 현재 엔티티를 병합할때 연관 엔티티도 병합
    *   - REMOVE : 현재 엔티티를 삭제할때 연관 엔티티도 삭제
    *   - REFRESH : 현재 엔티티를 새로고침(다시조회)할때 연관엔티티도 새로고침
    *   - ALL : 모두 적용
    * */
//    @ManyToOne  // fetch : Eager, cascade : 없음, optional : True
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;

}

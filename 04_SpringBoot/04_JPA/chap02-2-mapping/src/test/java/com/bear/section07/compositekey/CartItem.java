package com.bear.section07.compositekey;

import jakarta.persistence.*;

/*
 * [7-2] @EmbeddedId: 키 객체 하나(CartItemId)가 엔티티의 식별자
 *  - 테이블 lab_cart_item 의 기본 키 = (cart_no, product_code) 두 컬럼 → 콘솔 DDL: primary key (cart_no, product_code)
 *  - 키 필드 안의 컬럼들은 CartItemId 의 @Column 에서 온다
 *  - 복합 키는 자동 생성에 의존하지 않는다 → 값을 직접 넣는다
 */
@Entity(name = "CartItem")
@Table(name = "lab_cart_item")
public class CartItem {

    @EmbeddedId
    private CartItemId id;

    @Column(name = "quantity")
    private int quantity;

    public CartItem() {}

    public CartItem(CartItemId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public CartItemId getId() { return id; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "CartItem{id=" + id + ", quantity=" + quantity + "}";
    }
}

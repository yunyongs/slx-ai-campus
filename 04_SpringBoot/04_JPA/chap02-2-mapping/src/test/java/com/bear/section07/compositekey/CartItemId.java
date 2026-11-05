package com.bear.section07.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

/*
 * [7-2] @EmbeddedId 용 키 타입: 복합 키(장바구니 번호 + 상품 코드)를 "하나의 값 객체"로 묶는다
 *
 * 키 타입의 조건 (7-4)
 *  - @Embeddable
 *  - public 또는 protected 기본 생성자
 *  - equals() / hashCode() 구현 → 1차 캐시에서 "같은 키인가"를 값으로 비교하기 위해
 *    (구현하지 않으면 값이 같아도 다른 객체라서 다른 키로 취급된다 → G_CompositeKeyTest 세 번째 테스트)
 *  - JPA 3.2 에서는 Serializable 구현이 필수가 아니다
 */
@Embeddable
public class CartItemId {

    @Column(name = "cart_no")
    private Long cartNo;

    @Column(name = "product_code", length = 20)
    private String productCode;

    protected CartItemId() {}

    public CartItemId(Long cartNo, String productCode) {
        this.cartNo = cartNo;
        this.productCode = productCode;
    }

    public Long getCartNo() { return cartNo; }
    public String getProductCode() { return productCode; }

    // 두 키의 "값"이 같으면 같은 키
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemId)) return false;
        CartItemId other = (CartItemId) o;
        return Objects.equals(cartNo, other.cartNo) && Objects.equals(productCode, other.productCode);
    }

    // equals 가 같으면 hashCode 도 같아야 한다 (HashMap 같은 곳에서 키로 찾을 때 쓰임)
    @Override
    public int hashCode() {
        return Objects.hash(cartNo, productCode);
    }

    @Override
    public String toString() {
        return "CartItemId{cartNo=" + cartNo + ", productCode='" + productCode + "'}";
    }
}

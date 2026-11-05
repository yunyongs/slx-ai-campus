package com.bear.section07.compositekey;

import java.util.Objects;

/*
 * [7-3] @IdClass 용 키 클래스
 *  - @Embeddable 이 아니다. 엔티티(BookLike)의 @Id 필드들과 "이름·타입"을 똑같이 맞춘다
 *      BookLike.memberNo (Long) ↔ LikeKey.memberNo (Long)
 *      BookLike.bookNo   (Long) ↔ LikeKey.bookNo   (Long)
 *  - 기본 생성자 + equals() / hashCode() 필요 (7-4)
 *  - 조회할 때 find(BookLike.class, new LikeKey(회원번호, 책번호)) 처럼 "전체 키"를 이 객체로 넘긴다
 */
public class LikeKey {

    private Long memberNo;
    private Long bookNo;

    public LikeKey() {}

    public LikeKey(Long memberNo, Long bookNo) {
        this.memberNo = memberNo;
        this.bookNo = bookNo;
    }

    public Long getMemberNo() { return memberNo; }
    public Long getBookNo() { return bookNo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikeKey)) return false;
        LikeKey other = (LikeKey) o;
        return Objects.equals(memberNo, other.memberNo) && Objects.equals(bookNo, other.bookNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, bookNo);
    }
}

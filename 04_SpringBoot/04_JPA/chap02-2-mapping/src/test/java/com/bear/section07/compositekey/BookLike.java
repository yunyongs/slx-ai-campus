package com.bear.section07.compositekey;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/*
 * [7-3] @IdClass: 키 값들을 엔티티의 "개별 필드"로 선언하고, 각 필드에 @Id
 *  - "회원 1명이 책 1권에 좋아요" → (member_no, book_no) 조합이 기본 키 (같은 책에 두 번 좋아요 불가)
 *  - @IdClass(LikeKey.class): 이 엔티티의 키 타입이 LikeKey 라고 알려 준다
 *
 * @EmbeddedId 와 비교
 *  - CartItem   : item.getId().getCartNo()   ← 키 객체 안에 들어 있음
 *  - BookLike   : like.getMemberNo()         ← 엔티티 필드로 바로 보임
 */
@Entity(name = "BookLike")
@Table(name = "lab_book_like")
@IdClass(LikeKey.class)
public class BookLike {

    @Id
    @Column(name = "member_no")
    private Long memberNo;

    @Id
    @Column(name = "book_no")
    private Long bookNo;

    @Column(name = "liked_at")
    private LocalDateTime likedAt;

    public BookLike() {}

    public BookLike(Long memberNo, Long bookNo, LocalDateTime likedAt) {
        this.memberNo = memberNo;
        this.bookNo = bookNo;
        this.likedAt = likedAt;
    }

    public Long getMemberNo() { return memberNo; }
    public Long getBookNo() { return bookNo; }
    public LocalDateTime getLikedAt() { return likedAt; }

    @Override
    public String toString() {
        return "BookLike{memberNo=" + memberNo + ", bookNo=" + bookNo + ", likedAt=" + likedAt + "}";
    }
}

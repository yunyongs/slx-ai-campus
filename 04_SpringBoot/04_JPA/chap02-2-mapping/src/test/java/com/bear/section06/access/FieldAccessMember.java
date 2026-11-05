package com.bear.section06.access;

import jakarta.persistence.*;

/*
 * [6-2] FIELD 접근: JPA 가 필드에 "직접" 접근한다
 *  - 매핑 어노테이션(@Id, @Column)이 필드 위에 있다 → FIELD 접근으로 추론 (@Access 로 명시도 가능)
 *  - getter 에 가공 로직(뒤에 "님" 붙이기)이 있어도 JPA 는 getter 를 부르지 않으므로 DB 에는 원래 값이 저장된다.
 */
@Entity(name = "FieldAccessMember")
@Table(name = "lab_field_access_member")
@Access(AccessType.FIELD)
public class FieldAccessMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "nickname")
    private String nickname;

    public FieldAccessMember() {}

    public FieldAccessMember(String nickname) {
        this.nickname = nickname;
    }

    public Long getMemberNo() { return memberNo; }

    public String getNickname() {
        System.out.println("    FieldAccessMember.getNickname() 호출됨 (누가 불렀나?)");
        return nickname + "님";   // 화면용 가공 로직
    }

    public void setNickname(String nickname) {
        System.out.println("    FieldAccessMember.setNickname(" + nickname + ") 호출됨");
        this.nickname = nickname;
    }
}

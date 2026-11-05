package com.bear.section06.access;

import jakarta.persistence.*;

/*
 * [6-3] PROPERTY 접근: JPA 가 getter / setter 를 "통해서" 값을 읽고 쓴다
 *  - 매핑 어노테이션이 필드가 아니라 getter 위에 있다.
 *  - 저장할 때: getNickname() 의 결과("곰님")가 DB 에 들어간다.
 *  - 조회할 때: DB 값으로 setNickname(...) 이 호출된다.
 *  → getter 의 가공 로직이 영속 데이터에 영향을 준다 (F_AccessTypeTest 세 번째 테스트에서 함정 확인)
 *
 * 주의: PROPERTY 접근에서는 getXxx()/setXxx() 쌍이 전부 영속 속성으로 취급된다.
 *       매핑하지 않을 getter 를 추가하려면 그 getter 에 @Transient 를 붙여야 한다.
 */
@Entity(name = "PropertyAccessMember")
@Table(name = "lab_property_access_member")
@Access(AccessType.PROPERTY)
public class PropertyAccessMember {

    private Long memberNo;
    private String nickname;

    public PropertyAccessMember() {}

    public PropertyAccessMember(String nickname) {
        this.nickname = nickname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    public Long getMemberNo() { return memberNo; }

    public void setMemberNo(Long memberNo) { this.memberNo = memberNo; }

    @Column(name = "nickname")
    public String getNickname() {
        System.out.println("    PropertyAccessMember.getNickname() 호출됨 → \"" + nickname + "님\" 반환");
        return nickname + "님";   // 화면용 가공 로직 (같은 코드인데 FIELD 접근과 결과가 다르다!)
    }

    public void setNickname(String nickname) {
        System.out.println("    PropertyAccessMember.setNickname(\"" + nickname + "\") 호출됨");
        this.nickname = nickname;
    }
}

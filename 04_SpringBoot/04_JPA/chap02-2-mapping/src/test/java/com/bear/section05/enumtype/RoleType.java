package com.bear.section05.enumtype;

/*
 * [5-1] Enum: 값의 종류가 정해진 데이터 (역할, 상태, 등급 ...)
 *
 * ORDINAL 로 저장하면 "선언 순서 번호"가 들어간다: ADMIN = 0, MEMBER = 1
 *
 * 바꿔 보기: 아래 순서를 MEMBER, ADMIN 으로 바꾸면?
 *  → 이미 DB 에 0 으로 저장된 ADMIN 이, 다시 읽을 때 MEMBER 로 둔갑한다 (ORDINAL 의 위험)
 *  → STRING 으로 저장한 컬럼은 'ADMIN' 글자 그대로라 영향이 없다
 */
public enum RoleType {
    ADMIN, MEMBER
}

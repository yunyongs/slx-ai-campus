package com.bear.common;

import jakarta.persistence.EntityManager;

import java.util.List;

/*
 * 실습 도우미 (엔티티가 아니다)
 * - JPA 가 "어떤 테이블을 만들었고, 무엇을 저장했는지"를 엔티티 객체가 아니라 DB 쪽에서 직접 확인하려고 만든 클래스.
 * - createNativeQuery(sql): JPQL 이 아니라 MySQL SQL 을 그대로 실행한다 (지금은 "DB 안을 들여다보는 창" 정도로 이해하면 된다).
 */
public class SchemaPrinter {

    /*
     * information_schema.columns : MySQL 이 모든 테이블의 구조(컬럼 이름, 타입, NULL 허용, 기본값, 키)를 보관하는 시스템 테이블
     * DATABASE()                 : 지금 연결된 데이터베이스 이름 (persistence.xml 의 url → menudb)
     * :tableName                 : 이름 있는 파라미터 (PreparedStatement 의 ? 와 같은 역할 → SQL Injection 방지)
     */
    public static int printColumns(EntityManager entityManager, String tableName) {
        List<?> rows = entityManager.createNativeQuery(
                        "SELECT column_name, column_type, is_nullable, column_default, column_key "
                                + "FROM information_schema.columns "
                                + "WHERE table_schema = DATABASE() AND table_name = :tableName "
                                + "ORDER BY ordinal_position")
                .setParameter("tableName", tableName)
                .getResultList();

        System.out.println();
        System.out.println("===== [" + tableName + "] 컬럼 구조 (DB 에서 직접 조회) =====");
        System.out.println("컬럼 | 타입 | NULL 허용 | 기본값 | 키(PRI=기본키, UNI=유일)");
        for (Object row : rows) {
            Object[] cols = (Object[]) row;   // SELECT 한 컬럼 5개가 배열 한 칸씩 들어 있다
            System.out.println(cols[0] + " | " + cols[1] + " | " + cols[2] + " | " + cols[3] + " | " + cols[4]);
        }
        System.out.println("==========================================================");
        return rows.size();
    }

    /* 테이블의 행 개수. 테이블 이름은 우리가 정한 상수만 넘기므로 문자열로 붙인다 (사용자 입력이라면 이렇게 하면 안 된다) */
    public static long countRows(EntityManager entityManager, String tableName) {
        Object result = entityManager.createNativeQuery("SELECT COUNT(*) FROM " + tableName).getSingleResult();
        return ((Number) result).longValue();
    }

    /* 예외가 여러 겹으로 감싸져 올 때, 안쪽 원인까지 한 줄씩 출력 */
    public static void printCauses(Throwable e) {
        Throwable current = e;
        int depth = 0;
        while (current != null && depth < 5) {
            System.out.println("  " + "↳".repeat(depth) + current.getClass().getSimpleName() + ": " + current.getMessage());
            current = current.getCause();
            depth++;
        }
    }
}

package org.example.jpaintro.repository;


import org.example.jpaintro.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<엔티티 타입, 식별자 타입>
// 구현 클래스는 Spring Data JPA가 실행 시점에 만들어 준다.
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}

package org.example.jpaintro.service;

import lombok.RequiredArgsConstructor;
import org.example.jpaintro.dto.MenuRequestDTO;
import org.example.jpaintro.dto.MenuResponseDTO;
import org.example.jpaintro.entity.Menu;
import org.example.jpaintro.repository.MenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository; //MenuRepository로 전달한다 (MenuRepository가 다음 하위),
    // 그러나 MenuRepository는 인터페이스만 있고, 구현 시점에 Spring Data JPA가 만들어 준다

    public List<MenuResponseDTO> findAll() {

        //만들지 않아도 데이터베이스에서 조회 가능
        List<Menu> menuList = menuRepository.findAll();

        List<MenuResponseDTO> list = menuList.stream()
                .map(menu -> MenuResponseDTO.from(menu))
                .toList();

        return list;

    }

    @Transactional
    public MenuResponseDTO create(MenuRequestDTO request) {
        Menu menu = new Menu(
                request.getMenuName(),
                request.getMenuPrice(),
                request.getCategoryCode(),
                request.getOrderableStatus()
        );
        //DB에 저장
        Menu savedMenu = menuRepository.save(menu);

        return MenuResponseDTO.from(savedMenu);
    }

    // 수정 : 영속 상태의 엔티티 값을 바꾸면 트랜잭션 커밋 시점에 UPDATE 쿼리가 자동으로 나간다 (변경 감지, dirty checking)
    @Transactional
    public MenuResponseDTO update(Integer menuCode, MenuRequestDTO request) {

        // id 로 수정할 메뉴 정보 찾아오기
        Menu menu = menuRepository.findById(menuCode)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메뉴를 찾을 수 없습니다."));
        // 값 변경
        menu.change(
                request.getMenuName(),
                request.getMenuPrice(),
                request.getCategoryCode(),
                request.getOrderableStatus()
        );

        return MenuResponseDTO.from(menu);
    }

}

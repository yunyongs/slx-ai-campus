package org.example.jpaintro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.jpaintro.dto.MenuRequestDTO;
import org.example.jpaintro.dto.MenuResponseDTO;
import org.example.jpaintro.entity.Menu;
import org.example.jpaintro.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "메뉴 API", description = "tbl_menu 조회 / 등록 / 수정")
@RestController
@RequestMapping("/api/menus") //전체 주소
@RequiredArgsConstructor //필드의 초기화가 필요한 필드만 생성자로 만들어 주는 annotation
public class MenuController {

    private final MenuService menuService; //MenuService로 전달한다 (MenuService가 다음 하위)

    //전체 조회
    @GetMapping
    public List<MenuResponseDTO> findAll() {
        return menuService.findAll();
    }

    @PostMapping
    public MenuResponseDTO create(@RequestBody MenuRequestDTO menuRequestDTO) {
        return menuService.create(menuRequestDTO);
    }

    //수정 : PUT /api/menus/{menuCode}, 본문(JSON)의 값으로 통째로 덮어쓴다
    @Operation(summary = "메뉴 수정", description = "menuCode에 해당하는 메뉴를 요청 본문의 값으로 수정합니다.")
    @PutMapping("/{menuCode}") //PutMapping annotation을 통해 swagger에 PUT Http method가 나온다.
    public MenuResponseDTO update(@PathVariable Integer menuCode,
                                  @RequestBody MenuRequestDTO menuRequestDTO) {
        return menuService.update(menuCode, menuRequestDTO);
    }
}


//PostMapping / PutMapping은 @RequestBody가 필요함. SQL에서 WHERE=''의 값이 필요할 때 @PathVariable로 넘기면 된다.

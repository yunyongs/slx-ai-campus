package org.example.restapi.section04;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DtoModelAssembler implements RepresentationModelAssembler<UserDTO, EntityModel<UserDTO>> {
    /*
     * userDTO 객체를 EntityModel<UserDTO>로 감싸고 Hateoas 링크를 추가
     *
     * - linkTo : 컨트롤러 메서드에 대한 링크를 생성
     * - methodOn : 해당 컨트롤러 메서드를 호출하기위한 프록시 객체
     * - withSelfRel : 자신의 링크를 추가하겠다.
     * */
    @Override
    public EntityModel<UserDTO> toModel(UserDTO dto) {
        return EntityModel.of(
                dto,
                // 현재 사용자의 번호를 조회하는 URL을 만들고 -> 현재 리소스를 의미하는 self 링크로 추가한다.
                linkTo(methodOn(HateoasTestController.class).findUserByUserNo(dto.getNo())).withSelfRel(),
                linkTo(methodOn(HateoasTestController.class).findAllUsers()).withRel("users")
        );
    }
}

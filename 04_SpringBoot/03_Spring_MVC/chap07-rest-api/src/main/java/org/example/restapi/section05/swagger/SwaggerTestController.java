package org.example.restapi.section05.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "SWAGGER 테스트 컨트롤러", description = "Section #5의 스웨거 테스트입니다.")
public interface SwaggerTestController {

    // 유저 목록 전체 조회
    @Operation(summary = "모든 사용자 조회", description = "모든 사용자를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "500", description = "조회 실패", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<ResponseMessage> findAllUsers();

    // 특정 유저 상세 조회
    @Operation(summary = "특정 유저 상세 조회", description = "사용자 번호를 통해 특정 사용자 정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "500", description = "조회 실패", content = @Content(schema = @Schema(hidden = true)))
    })
    @Parameter(name = "userNo", description = "조회할 사용자 번호", required = true)
    public ResponseEntity<ResponseMessage> findUserByNo(int userNo);
}

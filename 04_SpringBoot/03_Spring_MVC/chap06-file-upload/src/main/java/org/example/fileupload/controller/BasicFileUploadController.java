package org.example.fileupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class BasicFileUploadController {

    @GetMapping("/basic-file-upload")
    public String basicFileUploadPage() {

        return "redirect:/";
    }

    /*
    * MultipartFile: 스프링에서 업로드 파일을 다루는 인터페이스
    * - 파일명, 크기, 타입, 내용을 확인할 수 있다.
    * - 저장을 위해서는 원하는 저장 경로로 옮겨야 한다.
    *
    * */
    @PostMapping("/basic-file-upload")
    public String uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, Model model) throws IOException {

/*        Path uploadDirectory1 = Paths.get("uploads").toAbsolutePath();
        System.out.println("uploadDirectory1 = " + uploadDirectory1);
        Path uploadDirectory2= Paths.get("uploads").toAbsolutePath();
        System.out.println("uploadDirectory2 = " + uploadDirectory2);*/
        Path uploadDirectory = Paths.get("uploads").normalize();
        
        //업로드 디렉토리 생성, 이미 있다면 아무 일도 일어나지 않음
        Files.createDirectories(uploadDirectory);
        
        //파일 가져오기
        String originalFile = file.getOriginalFilename();
        System.out.println("originalFile = " + originalFile);
        
        Path savedFile = uploadDirectory.resolve(originalFile);
        System.out.println("savedFile = " + savedFile);

        /*
        * MultipartFile의 실제 내용을 지정한 경로에 저장한다. 즉 경로 지정한 파일을 실제로 저장(전달)한다.
        * */
        file.transferTo(savedFile);
        model.addAttribute("message", "파일 업로드 성공");
        model.addAttribute("originalFileName", originalFile);
        model.addAttribute("savedPath", savedFile);


        return "main";
    }

    /*
    * 문제점
    * 1. 파일 경로가 Java 코드에 직접 작성되어 있음
    * -> 운영체제나 실행환경마다 다를 때 코드를 수정해야 하는 문제
    * :: 해결책: yaml에 OS 별 경로를 설정해서 @Value로 주입하는 방식으로 해결
    * 2. 같은 이름의 파일이 (항상-그리고 묻지 않고) 덮어 씌워짐
    * -> 여러 사용자가 같은 파일명을 업로드하면 기존 파일이 삭제될 수 있음.
    * :: 해결책: UUID 기반의 고유한 저장 파일명을 생성
    * 3. 파일을 저장하고 나서 화면(브라우저)에서 조회 불가
    * -> 파일 시스템 경로는 브라우저가 조회할 수 없기에,
    * :: 해결책: WebMvcConfigurer의 addResourceHandlers()로 URL과 실제 저장 경로와 연결해서 보여줄 것
    * */
}

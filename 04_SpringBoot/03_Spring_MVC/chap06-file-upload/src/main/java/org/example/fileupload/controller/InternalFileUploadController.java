package org.example.fileupload.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class InternalFileUploadController {


    @GetMapping("/result")
    public String result() {
        return "result";
    }
    /*
    * ResourceLoader
    * - classpath와 같이 Spring이 관리하는 리소스의 위치를 Resource객체로 조회
    * */
    private final ResourceLoader resourceLoader;

    public InternalFileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostMapping("single-file")
    public String singleFileUpload(
            @RequestParam MultipartFile singleFile,
            @RequestParam String singleFileDescription,
            RedirectAttributes redirectAttributes
            ) throws IOException{

        String filePath = getInternalUploadPath("single");
        String originalFileName = singleFile.getOriginalFilename();

        /*
        * UUID
        * */
        String savedName = createSavedName(originalFileName);

        singleFile.transferTo(new File(filePath, savedName));
        redirectAttributes.addFlashAttribute("message", "단일 파일 업로드 성공!");
        redirectAttributes.addFlashAttribute("img", "/uploadedFiles/img/single/" + savedName);
        redirectAttributes.addFlashAttribute("singleFileDescription", singleFileDescription);

        return "redirect:/result";
    }

    private String createSavedName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자 가져오기

        // UUID 문자열로 변환 후 "-"지우고 확장자 붙이기
        return UUID.randomUUID().toString().replace("-", "") + extension;
    }

    // Upload할 경로를 반환해주는 메서드
    private String getInternalUploadPath(String directoryName) throws IOException {

        String classLocation = "classpath:static/uploadedFiles/img/" + directoryName;
        Resource resource = resourceLoader.getResource(classLocation);

        // 있으면 절대경로를 반환하고
        if(resource.exists())  {
            return resource.getFile().getAbsolutePath();
        }

        // 없으면 폴더를 생성하고 절대경로를 반환
        File directory = new File("src/main/resources/static/uploadedFiles/img/" + directoryName);
        directory.mkdirs();
        return directory.getAbsolutePath();
    }
}

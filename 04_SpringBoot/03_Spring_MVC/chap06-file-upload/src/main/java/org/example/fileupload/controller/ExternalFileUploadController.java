package org.example.fileupload.controller;

import org.example.fileupload.dto.FileDTO;
import org.example.fileupload.util.FileUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ExternalFileUploadController {

    private final FileUploadUtils fileUploadUtils;

    public ExternalFileUploadController(FileUploadUtils fileUploadUtils) {
        this.fileUploadUtils = fileUploadUtils;
    }

    @PostMapping("external-single-file")
    public String externalSingleFileUpload(
            @RequestParam MultipartFile singleFile,
            @RequestParam String singleFileDescription,
            RedirectAttributes redirectAttributes
            ) throws IOException{

        //여러개의 파일 업로드시 DTO로 활용
        /*
        * @RequestParam MultipartFile singleFile, * @RequestParam MultipartFile singleFile2,
            @RequestParam String singleFileDescription,
        * */

        // 외부 폴더: yaml 경로 + /img/single
        String filePath = fileUploadUtils.getUploadPath("img/single");
        String originalFileName = singleFile.getOriginalFilename();

        /*
        * UUID
        * */
        String savedName = createSavedName(originalFileName);

        singleFile.transferTo(new File(filePath, savedName));

        redirectAttributes.addFlashAttribute("message", "외부 단일 파일 업로드 성공!");

        //img: 서버로 이미지에 대한 요청을 보낼 주소 (WebConfig의 /uploads/** 와 연결)
        redirectAttributes.addFlashAttribute("img", "/uploads/img/single/" + savedName);
        redirectAttributes.addFlashAttribute("singleFileDescription", singleFileDescription);

        return "redirect:/result";
    }

    private String createSavedName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자 가져오기

        // UUID 문자열로 변환 후 "-"지우고 확장자 붙이기
        return UUID.randomUUID().toString().replace("-", "") + extension;
    }

    @PostMapping("external-multi-file")
    public String externalMultiFileUpload(@RequestParam List<MultipartFile> multiFiles,
                                          @RequestParam String multiFileDescription,
                                          RedirectAttributes redirectAttributes) {

        // 여러 파일들도 하나씩 저장하므로 Multipart File 과정을 반복
        String uploadPath = fileUploadUtils.getUploadPath("img/multi");
        List<FileDTO> files = new ArrayList<>();
        List<String> savedFilesPaths = new ArrayList<>();

        try {
            for (MultipartFile multiFile : multiFiles) {
                String originalFileName = multiFile.getOriginalFilename();
                String savedName = createSavedName(originalFileName);

                files.add(new FileDTO(originalFileName, savedName, uploadPath, multiFileDescription));
                multiFile.transferTo(new File(uploadPath, savedName));
                savedFilesPaths.add("/uploads/img/multi/" + savedName);
            }

            redirectAttributes.addFlashAttribute("message", "[Success] 외부 경로에 다중 파일 업로드 성공!");
            redirectAttributes.addFlashAttribute("imgs", savedFilesPaths);
            redirectAttributes.addFlashAttribute("multiFileDescription", multiFileDescription);
        } catch (Exception exception) {
            for (FileDTO file : files) {
                new File(uploadPath, file.getSavedName()).delete();
            }
            redirectAttributes.addFlashAttribute("message", "[Failed] 외부 경로에 다중 파일 업로드 실패!!");
        }

        return "redirect:/result";
    }


}

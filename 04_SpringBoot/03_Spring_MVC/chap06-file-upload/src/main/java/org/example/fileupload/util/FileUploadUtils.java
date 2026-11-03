package org.example.fileupload.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileUploadUtils {

    /*
    * @Value
    * - application.yaml 등의 설정값을 변수에 주입하는 어노테이션
    * - 설정파일의 해당 경로값을 가져와서 주입해 준다.
    * */
    @Value("${file.upload.mac.path}")
    private String macPath;

    @Value("${file.upload.windows.path}")
    private String windowsPath;

    // 현재 OS에 맞는 업로드 기본 경로를 반환
    public String getBasePath() {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("win")) {
            return windowsPath;
        } else {
            return macPath;
        }
    }

    public String getUploadPath(String subdirectory) {
        String uploadPath = getBasePath() + "/" + subdirectory;
        File directory = new File(uploadPath);

        if(!directory.exists()) {
            System.out.println("file directory created");
            // mkdirs(): 중간 폴더(img)까지 한 번에 생성
            boolean created = directory.mkdirs();
            if(!created) {
                throw new RuntimeException("Error occurred while file upload" + uploadPath);
            }
        }

        return uploadPath;
    }
}

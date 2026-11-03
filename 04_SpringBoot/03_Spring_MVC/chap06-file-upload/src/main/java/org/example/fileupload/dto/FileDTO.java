package org.example.fileupload.dto;

import java.util.Objects;

public class FileDTO {

    public String getSavedName() {
    }

    public class FileDTO {

        private String originalFileName;
        private String savedName;
        private String filePath;
        private String fileDescription;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FileDTO fileDTO = (FileDTO) o;
            return Objects.equals(originalFileName, fileDTO.originalFileName) && Objects.equals(savedName, fileDTO.savedName) && Objects.equals(filePath, fileDTO.filePath) && Objects.equals(fileDescription, fileDTO.fileDescription);
        }

        @Override
        public int hashCode() {
            return Objects.hash(originalFileName, savedName, filePath, fileDescription);
        }

        @Override
        public String toString() {
            return "FileDTO{" +
                    "originalFileName='" + originalFileName + '\'' +
                    ", savedName='" + savedName + '\'' +
                    ", filePath='" + filePath + '\'' +
                    ", fileDescription='" + fileDescription + '\'' +
                    '}';
        }

    }

}


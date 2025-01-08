package com.metacoding.restserver._core.util;

import com.metacoding.restserver._core.error.exception.Exception500;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class MyFileUtil {
    public static String fileSave(String base64) {

        // 1. 확장자 추출
        String mimeType = base64.substring(5, base64.indexOf(";base64,"));
        String result = mimeType.split("/")[1];

        // 2. Base64를 Byte 배열로 변환
        String imgName = UUID.randomUUID()+"."+result;
        String profileUrl = "images/"+imgName;
        String dbUrl = "/images/"+imgName;

        String base64Data = base64.split(",")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        // 3. DTO에 사진을 파일로 저장 (images 폴더)
        try {
            Path path = Paths.get(profileUrl);
            Files.write(path, decodedBytes);
            return dbUrl;
        } catch (Exception e) {
            throw new Exception500(e.getMessage());
        }
    }

    public static String fileSave(MultipartFile file) {
        // 1. DTO에 사진파일명을 롤링 시킨다.
        String imgName = UUID.randomUUID()+"_"+file.getOriginalFilename();
        String profileUrl = "images/"+imgName;
        String dbUrl = "/images/"+imgName;

        // 2. DTO에 사진을 파일로 저장 (images 폴더)
        try {
            Path path = Paths.get(profileUrl);
            Files.write(path, file.getBytes());
            return dbUrl;
        } catch (Exception e) {
            throw new Exception500(e.getMessage());
        }
    }
}
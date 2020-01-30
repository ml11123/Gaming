package com.gaming.baby.controller.admin;

import com.gaming.baby.payload.response.SingleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin/upload")
public class AdminUploadController {

    @Value("${com.gaming.baby.uploadDir}")
    private String UPLOAD_DIR;

    @Value("${gaming.baby.apiDomain}")
    private String apiDomain;

    @PostMapping("/single")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadFile, @RequestParam("title") String title, @RequestParam("content") String content){



        if(uploadFile.isEmpty()){
            return new ResponseEntity("Please Select a file", HttpStatus.BAD_REQUEST);
        }

        try{
            saveUploadedFile(uploadFile);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new SingleResponse(true, "Successfully uploaded", "http://" + apiDomain + "/uploads/" + uploadFile.getOriginalFilename()), HttpStatus.OK);
    }

    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        for(MultipartFile file : files){

            if(file.isEmpty()){
                continue;
            }

            saveUploadedFile(file);
        }
    }

    private void saveUploadedFile(MultipartFile file) throws IOException {

        if(file.isEmpty()) return;

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, bytes);
    }
}

package com.gaming.baby.controller.client;

import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/files")
public class DownloadController {

    @Value("${com.gaming.baby.uploadDir}")
    private String uploadDir;

    @GetMapping("/{filePath:.+}/{fileName:.+}")
    public ResponseEntity<?> download(@PathVariable String filePath, @PathVariable String fileName){
        Path path = Paths.get(uploadDir + filePath + "/" + fileName);
        Resource resource = null;

        try{
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new ResourceNotFoundException("File not found");
        }

        String contentType = URLConnection.guessContentTypeFromName(resource.getFilename());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment' filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}

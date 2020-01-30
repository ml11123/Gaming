package com.gaming.baby.controller.admin;

import com.gaming.baby.entity.News;
import com.gaming.baby.payload.request.SearchRequest;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin/news")
public class AdminNewsController {

    @Autowired
    private NewsRepository newsRepository;


    @Value("${com.gaming.baby.uploadDir}")
    private String UPLOAD_DIR;

    private void saveUploadedFile(MultipartFile file) throws IOException {

        if(file.isEmpty()) return;

        Path path = Paths.get(UPLOAD_DIR + "news");
        if(Files.notExists(path)){

            boolean ok = path.toFile().mkdirs();

        }

        byte[] bytes = file.getBytes();
        Path filePath = Paths.get(UPLOAD_DIR + "news/" + file.getOriginalFilename());
        Files.write(filePath, bytes);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam("file") Optional<MultipartFile> uploadFile,
                                 @RequestParam("id") Optional<Long> id,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content){

        News news = null;

        if(id.isPresent()){
            news = newsRepository.findById(id);
            news.setTitle(title);
            news.setContent(content);
        }
        else{
            news = new News(title, content);
        }

        if(uploadFile.isPresent()){
            try{
                saveUploadedFile(uploadFile.get());

                news.setImage(uploadFile.get().getOriginalFilename());

            } catch (IOException ignored) {

            }
        }

        newsRepository.save(news);


        return new ResponseEntity<>(new SingleResponse(true, "Success", news), HttpStatus.OK);

    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<News> list(@RequestBody SearchRequest searchRequest){

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPerPage(), Sort.by("datetime").descending());

        return newsRepository.findAll(pageable);

    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public News get(@RequestParam String slug){
        return newsRepository.findNewsBySlug(slug);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("id") long id){

        News news = newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Item not found")
        );
        newsRepository.delete(news);

        return new ResponseEntity<>(new SingleResponse(true, "Delete successfully", news), HttpStatus.OK);
    }

}

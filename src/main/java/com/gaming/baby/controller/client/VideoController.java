package com.gaming.baby.controller.client;

import com.gaming.baby.entity.Video;
import com.gaming.baby.entity.VideoTag;
import com.gaming.baby.payload.request.SearchRequest;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.VideoRepository;
import com.gaming.baby.repository.VideoTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    VideoTagRepository videoTagRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody SearchRequest searchRequest){
        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPerPage(), searchRequest.getSortObj());

        Page<Video> list = videoRepository.findAll(pageable);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ResponseEntity<?> filter(@RequestParam("tagName") Optional<String> tagName){

        Set<Video> videos = null;
        if(tagName.isPresent()){
            VideoTag tag = videoTagRepository.findByName(tagName.get()).orElseThrow(
                    () -> new EntityNotFoundException("Tag not found")
            );
            videos = tag.getVideos();
        }

        else{
            videos = new HashSet<>(videoRepository.findAll());
        }

        return new ResponseEntity<>(new SingleResponse(true, "Success", videos), HttpStatus.OK);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<?> detail(@RequestParam long id){
        Video video = videoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Video not found")
        );

        return new ResponseEntity<>(video, HttpStatus.OK);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResponseEntity<?> listVideoTags(){
        return new ResponseEntity<>(videoTagRepository.findAll(), HttpStatus.OK);
    }





}

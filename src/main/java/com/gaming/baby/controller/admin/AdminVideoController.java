package com.gaming.baby.controller.admin;

import com.gaming.baby.entity.Video;
import com.gaming.baby.entity.VideoCategory;
import com.gaming.baby.entity.VideoTag;
import com.gaming.baby.payload.request.AddNewVideoRequest;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.VideoCategoryRepository;
import com.gaming.baby.repository.VideoRepository;
import com.gaming.baby.repository.VideoTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/admin/video")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminVideoController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoCategoryRepository videoCategoryRepository;

    @Autowired
    private VideoTagRepository videoTagRepository;

    private Set<VideoTag> videoTagsFromName(Set<String> tagsName){
        Set<VideoTag> videoTags = new HashSet<>();
        for (String tagName : tagsName){
            VideoTag tag = videoTagRepository.findByName(tagName).orElse(new VideoTag(tagName));

            videoTagRepository.save(tag);
            videoTags.add(tag);
        }

        return videoTags;
    }

    private VideoCategory videoCategoryFromName(String categoryName){
        VideoCategory category = videoCategoryRepository.findByName(categoryName);

        if(category == null){
            category = new VideoCategory(categoryName);
            videoCategoryRepository.save(category);
        }

        return category;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AddNewVideoRequest newVideoRequest){

        Video video = null;
        if(newVideoRequest.getId() > 0){
            video = videoRepository.findById(newVideoRequest.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Video not found")
            );
        }
        else{
            video = new Video();
        }

        video.setName(newVideoRequest.getName());
        video.setSrc(newVideoRequest.getSrc());
        video.setThumbnail(newVideoRequest.getThumbnail());
        video.setCoin(newVideoRequest.getCoin());
        video.setIntro(newVideoRequest.getIntro());
        video.setCategory(videoCategoryFromName(newVideoRequest.getCategoryName()));
        video.setTags(videoTagsFromName(newVideoRequest.getTagsName()));
        video.setDisplay(newVideoRequest.isDisplay());
        video.setCover(newVideoRequest.getCover());
        video.setActor(newVideoRequest.getActress());

        videoRepository.save(video);


        return new ResponseEntity<>(new SingleResponse(true, "Add successfully", video), HttpStatus.OK);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("id") long id){

        Video video = videoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Video not found")
        );

        videoRepository.delete(video);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }




}

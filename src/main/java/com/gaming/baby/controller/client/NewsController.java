package com.gaming.baby.controller.client;

import com.gaming.baby.entity.News;
import com.gaming.baby.payload.request.SearchRequest;
import com.gaming.baby.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<News> listNews(@RequestBody SearchRequest searchRequest){

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPerPage());

        return newsRepository.findAll(pageable);

    }
}

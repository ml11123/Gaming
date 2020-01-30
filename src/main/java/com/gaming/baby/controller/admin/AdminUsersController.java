package com.gaming.baby.controller.admin;

import com.gaming.baby.entity.Users;
import com.gaming.baby.payload.request.SearchRequest;
import com.gaming.baby.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin/users")
public class AdminUsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<Users> list(@RequestBody SearchRequest searchRequest){

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPerPage(), searchRequest.getSortObj());

        return userRepository.findAll(pageable);
    }

}

package com.gaming.baby.controller.admin;

import com.gaming.baby.entity.Deposit;
import com.gaming.baby.payload.request.SearchRequest;
import com.gaming.baby.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin/deposit")
public class AdminDepositController {

    @Autowired
    private DepositRepository depositRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<Deposit> list(@RequestBody SearchRequest searchRequest){
        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPerPage(), Sort.by("datetime").descending());

        return depositRepository.findAll(pageable);
    }
}

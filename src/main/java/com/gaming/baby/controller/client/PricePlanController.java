package com.gaming.baby.controller.client;

import com.gaming.baby.entity.PricePlan;
import com.gaming.baby.repository.PricePlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/price-plan")
public class PricePlanController {

    @Autowired
    private PricePlanRepository pricePlanRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<PricePlan> list(){
        return pricePlanRepository.findAll();
    }


}

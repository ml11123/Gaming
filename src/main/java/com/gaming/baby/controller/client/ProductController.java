package com.gaming.baby.controller.client;

import com.gaming.baby.entity.Product;
import com.gaming.baby.entity.ProductTag;
import com.gaming.baby.entity.Video;
import com.gaming.baby.entity.VideoTag;
import com.gaming.baby.payload.request.SearchRequest;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.ProductRepository;
import com.gaming.baby.repository.ProductTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductTagRepository productTagRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody SearchRequest searchRequest){

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPerPage(), searchRequest.getSortObj());

        Page<Product> list = productRepository.findAll(pageable);

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public ResponseEntity<?> filter(@RequestParam("tagName") Optional<String> tagName){

        Set<Product> products = null;
        if(tagName.isPresent()){
            ProductTag tag = productTagRepository.findByName(tagName.get()).orElseThrow(
                    () -> new EntityNotFoundException("Tag not found")
            );
            products = tag.getProducts();
        }

        else{
            products = new HashSet<>(productRepository.findAll());
        }

        return new ResponseEntity<>(new SingleResponse(true, "Success", products), HttpStatus.OK);
    }

    @RequestMapping(value = "/detail")
    public ResponseEntity<?> detail(@RequestParam long id){

        Product product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found")
        );

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public ResponseEntity<?> tags(){
        return new ResponseEntity<>(productTagRepository.findAll(), HttpStatus.OK);
    }
}

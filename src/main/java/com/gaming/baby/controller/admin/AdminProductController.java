package com.gaming.baby.controller.admin;

import com.gaming.baby.entity.*;
import com.gaming.baby.payload.request.AddProductRequest;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.ProductCategoryRepository;
import com.gaming.baby.repository.ProductImageRepository;
import com.gaming.baby.repository.ProductRepository;
import com.gaming.baby.repository.ProductTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductTagRepository productTagRepository;

    @Autowired
    private ProductImageRepository productImageRepository;


    private Set<ProductTag> productTagsFromName(Set<String> tagsName){
        Set<ProductTag> productTags = new HashSet<>();
        for (String tagName : tagsName){
            ProductTag tag = productTagRepository.findByName(tagName).orElse(new ProductTag(tagName));

            productTagRepository.save(tag);
            productTags.add(tag);
        }

        return productTags;
    }

    private ProductCategory productCategoryFromName(String categoryName){
        ProductCategory category = productCategoryRepository.findByName(categoryName);

        if(category == null){
            category = new ProductCategory(categoryName);
            productCategoryRepository.save(category);
        }

        return category;
    }




    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody AddProductRequest productRequest){

        Product product = productRepository.findById(productRequest.getId()).orElse(
                new Product()
        );

        product.setName(productRequest.getName());
        product.setCoin(productRequest.getCoin());
        product.setIntro(productRequest.getIntro());
        product.setThumbnail(productRequest.getThumbnail());
        product.setDisplay(productRequest.isDisplay());
        product.setCategory(productCategoryFromName(productRequest.getCategoryName()));
        product.setTags(productTagsFromName(productRequest.getTagsName()));


        productRepository.save(product);

        Set<ProductImage> productImages = new HashSet<>();

        for(String src : productRequest.getImagesSrc()){

            ProductImage image = productImageRepository.findBySrc(src).orElse(
                    new ProductImage(product, src)
            );

            productImageRepository.save(image);
            productImages.add(image);

        }

        product.setImages(productImages);

        return new ResponseEntity<>(new SingleResponse(true, "Success", product), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("id") long id){

        Product product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product not found")
        );

        productRepository.delete(product);

        return new ResponseEntity<>(new SingleResponse(true, "Success", product), HttpStatus.OK);

    }



}

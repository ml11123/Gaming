package com.gaming.baby.repository;

import com.gaming.baby.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

    Optional<ProductTag> findByName(String name);
}

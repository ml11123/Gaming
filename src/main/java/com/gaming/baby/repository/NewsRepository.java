package com.gaming.baby.repository;


import com.gaming.baby.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    News findNewsBySlug(String slug);

    List<News> findAllBySlug(String slug, Pageable pageable);

    Page<News> findAll(Pageable pageable);

    News findById(Optional<Long> id);

}

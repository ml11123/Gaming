package com.gaming.baby.repository;

import com.gaming.baby.entity.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {

    VideoCategory findByName(String name);
}

package com.gaming.baby.repository;

import com.gaming.baby.entity.VideoTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoTagRepository extends JpaRepository<VideoTag, Long> {

    Optional<VideoTag> findByName(String name);
}

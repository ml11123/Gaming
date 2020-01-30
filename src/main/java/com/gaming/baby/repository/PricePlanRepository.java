package com.gaming.baby.repository;

import com.gaming.baby.entity.PricePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricePlanRepository extends JpaRepository<PricePlan, Long> {

}

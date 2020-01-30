package com.gaming.baby.repository;

import com.gaming.baby.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
    Users findByPhone(String phone);
    Users findByUsername(String username);
    Users findByUID(String uid);
}

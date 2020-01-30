package com.gaming.baby.repository;

import com.gaming.baby.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Page<Ticket> findAll(Pageable pageable);
    Ticket findTicketById(long id);

    @Transactional
    Page<Ticket> findByUserId(Long uid, Pageable pageable);
}

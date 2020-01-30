package com.gaming.baby.controller.admin;

import com.gaming.baby.payload.response.DashboardInfo;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.DepositRepository;
import com.gaming.baby.repository.TicketMessageRepository;
import com.gaming.baby.repository.TicketRepository;
import com.gaming.baby.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin/dashboard")
public class AdminController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMessageRepository ticketMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepositRepository depositRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/unReplyTicketCount", method = RequestMethod.GET)
    public ResponseEntity<?> unReplyTicketCount(){

        long unReplyTicketCount = entityManager.createQuery("SELECT COUNT(ticket.id) FROM Ticket ticket WHERE ticket.id NOT IN (SELECT ticket_message.ticket.id FROM TicketMessage ticket_message)", Long.class).getSingleResult();

        long ticketCount = ticketRepository.count();

        long userCount = entityManager.createQuery("SELECT COUNT(users.id) FROM Users users LEFT JOIN Role role ON users.role.id = role.id WHERE role.name = ?1", Long.class).setParameter(1, "USER").getSingleResult();

        DashboardInfo info = new DashboardInfo(userCount, ticketCount, unReplyTicketCount, 0, 0);

        return new ResponseEntity<>(new SingleResponse(true, "Success", info), HttpStatus.OK);
    }


}

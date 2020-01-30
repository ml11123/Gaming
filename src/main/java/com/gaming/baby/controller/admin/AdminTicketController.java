package com.gaming.baby.controller.admin;

import com.gaming.baby.entity.Ticket;
import com.gaming.baby.entity.TicketMessage;
import com.gaming.baby.entity.Users;
import com.gaming.baby.jwt.TokenProvider;
import com.gaming.baby.payload.request.ReplyTicketRequest;
import com.gaming.baby.payload.request.SearchRequest;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.TicketMessageRepository;
import com.gaming.baby.repository.TicketRepository;
import com.gaming.baby.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin/ticket")
public class AdminTicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMessageRepository ticketMessageRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<Ticket> list(@RequestBody SearchRequest searchRequest){

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPerPage(), Sort.by("datetime").descending());

        return ticketRepository.findAll(pageable);
    }

    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse replyTicket(@RequestBody ReplyTicketRequest replyTicketRequest){

        Ticket ticket = ticketRepository.findById(replyTicketRequest.getTicket_id()).orElseThrow(
                () -> new EntityNotFoundException("Ticket not found")
        );

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];

        long user_id = tokenProvider.getUserIdFromJwt(token);

        Users user = userRepository.findById(user_id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id: " + user_id)
        );

        ticket.setUpdateTime(new Date());

        TicketMessage ticketMessage = new TicketMessage(ticket, user, replyTicketRequest.getMessage());

        ticketRepository.save(ticket);
        ticketMessageRepository.save(ticketMessage);

        return new SingleResponse(true, "Success", ticketMessage);

    }
}

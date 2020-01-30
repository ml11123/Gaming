package com.gaming.baby.controller.client;

import com.gaming.baby.entity.Ticket;
import com.gaming.baby.entity.Users;
import com.gaming.baby.jwt.TokenProvider;
import com.gaming.baby.payload.request.SearchRequest;
import com.gaming.baby.payload.request.TicketRequest;
import com.gaming.baby.payload.response.SingleResponse;
import com.gaming.baby.repository.TicketRepository;
import com.gaming.baby.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    private Users getUserFromJWT(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];

        long user_id = tokenProvider.getUserIdFromJwt(token);

        Users user = userRepository.findById(user_id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id: " + user_id)
        );

        return user;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Page<Ticket> list(@RequestBody SearchRequest searchRequest){

        Users user = getUserFromJWT();

        Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getPerPage(), Sort.by("datetime").descending());

        return ticketRepository.findByUserId(user.getId(), pageable);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Ticket detail(@RequestParam long id){
        return ticketRepository.findTicketById(id);
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public SingleResponse submit(@RequestBody TicketRequest ticketRequest){


        Users user = getUserFromJWT();


        Ticket ticket = new Ticket(user, ticketRequest.getIssue(), ticketRequest.getDescription());

        ticketRepository.save(ticket);


        return new SingleResponse(true, "Success", ticket);

    }

}

package com.kbtg.bootcamp.posttest.userticket;

import java.util.List;

import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserTicketController {

    private final UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<ResponseUserTicketId> buyUserTicketByTicketId(
            @Pattern(regexp = "\\d{10}", message = "User ID must be 10 digits") @PathVariable String userId,
            @Pattern(regexp = "\\d{6}", message = "Ticket ID must be 6 digits") @PathVariable String ticketId) throws Exception {
        String id = userTicketService.buyUserTicketByTicketId(userId, ticketId);
        return new ResponseEntity<>(new ResponseUserTicketId(id), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/lotteries")
    public ResponseEntity<ResponseUserTickets> getAllUserTicketByUser(
            @Pattern(regexp = "\\d{10}", message = "User ID must be 10 digits") @PathVariable String userId) throws Exception {
        UserTicketDetail lotteriedDetail = userTicketService.getAllLotteryByUser(userId);
        return ResponseEntity.ok(new ResponseUserTickets(lotteriedDetail.getTickets(), lotteriedDetail.getCount(), lotteriedDetail.getCost()));
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<ResponseTicket> deleteUserTicket(
            @Pattern(regexp = "\\d{10}", message = "User ID must be 10 digits") @PathVariable String userId,
            @Pattern(regexp = "\\d{6}", message = "Ticket ID must be 6 digits") @PathVariable String ticketId) {
        String ticket = userTicketService.deleteUserTicket(userId, ticketId);
        return ResponseEntity.ok(new ResponseTicket(ticket));
    }
}

record ResponseUserTicketId(String id) {
}

record ResponseTicket(String ticket) {
}

record ResponseUserTickets(List<String> tickets, Integer count, Double cost) {
}

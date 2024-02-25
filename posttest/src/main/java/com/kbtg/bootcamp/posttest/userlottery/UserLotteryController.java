package com.kbtg.bootcamp.posttest.userlottery;

import java.util.List;

import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserLotteryController {

    private final UserLotteryService userLotteryService;

    public UserLotteryController(UserLotteryService userLotteryService) {
        this.userLotteryService = userLotteryService;
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<ResponseUserLotteryId> buyUserLotteryByTicketId(
            @Pattern(regexp = "\\d{10}", message = "User ID must be 10 digits") @PathVariable String userId,
            @Pattern(regexp = "\\d{6}", message = "Ticket ID must be 6 digits") @PathVariable String ticketId) throws Exception {
        String id = userLotteryService.buyUserLotteryByTicketId(userId, ticketId);
        return new ResponseEntity<>(new ResponseUserLotteryId(id), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/lotteries")
    public ResponseEntity<ResponseUserLotteries> getAllLotteryByUser(
            @Pattern(regexp = "\\d{10}", message = "User ID must be 10 digits") @PathVariable String userId) throws Exception {
        UserLotteryDetail lotteriedDetail = userLotteryService.getAllLotteryByUser(userId);
        return ResponseEntity.ok(new ResponseUserLotteries(lotteriedDetail.getTickets(), lotteriedDetail.getCount(), lotteriedDetail.getCost()));
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<ResponseLottery> deleteUserLottery(
            @Pattern(regexp = "\\d{10}", message = "User ID must be 10 digits") @PathVariable String userId,
            @Pattern(regexp = "\\d{6}", message = "Ticket ID must be 6 digits") @PathVariable String ticketId) {
        String ticket = userLotteryService.deleteUserLottery(userId, ticketId);
        return ResponseEntity.ok(new ResponseLottery(ticket));
    }
}

record ResponseUserLotteryId(String id) {
}

record ResponseLottery(String ticket) {
}

record ResponseUserLotteries(List<String> tickets, Integer count, Double cost) {
}

package com.kbtg.bootcamp.posttest.userlottery;

import com.kbtg.bootcamp.posttest.lottery.LotteryService;

import java.util.List;

import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserLotteryController {

    UserLotteryService userLotteryService;
    LotteryService lotteryService;

    public UserLotteryController(UserLotteryService userLotteryService ,LotteryService lotteryService) {
        this.userLotteryService = userLotteryService;
        this.lotteryService = lotteryService;
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public ResponseUserLotteryId buyUserLotteryByTicketId(@PathVariable Integer userId ,@Size(min = 6, max = 6) @PathVariable String ticketId) {
        String id = userLotteryService.buyUserLotteryByTicketId(userId, ticketId);
        return new ResponseUserLotteryId(id);
    }

    @GetMapping("/{userId}/lotteries")
    public ResponseUserLotteries getAllLotteryByUser(@PathVariable Integer userId) {
        UserLotteryDetail lotteriedDetail = userLotteryService.getAllLotteryByUser(userId);
        return new ResponseUserLotteries(lotteriedDetail.getTickets(),lotteriedDetail.getCount(),lotteriedDetail.getCost());
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public ResponseLottery deleteUserLottery(@PathVariable Integer userId ,@Size(min = 6, max = 6) @PathVariable String ticketId) {
         userLotteryService.deleteUserLottery(userId, ticketId);
         return new ResponseLottery(ticketId);
    }
}

record ResponseUserLotteryId(String id) {}
record ResponseLottery(String ticket) {}
record ResponseUserLotteries(List<String> tickets , Integer count ,Double cost) {}

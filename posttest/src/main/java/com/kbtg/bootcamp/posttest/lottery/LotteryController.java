package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {
    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseLotteryList> getLotteryList() {
        return ResponseEntity.ok(new ResponseLotteryList(lotteryService.getLotteryList()));
    }
}

record ResponseLotteryList(List<String> tickets) {
}

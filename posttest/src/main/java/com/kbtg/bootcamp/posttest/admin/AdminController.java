package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final LotteryService lotteryService;

    public AdminController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/lotteries")
    public ResponseEntity<ResponseLotteryList> getLotteryList() {
        return ResponseEntity.ok(new ResponseLotteryList(lotteryService.getLotteryList()));
    }

    @PostMapping("/lotteries")
    public ResponseEntity<ResponseLottery> addLottery(@Valid @RequestBody LotteryRequestDto lotteryDto) throws Exception {
        Lottery lottery = new Lottery();
        lottery.setTicket(lotteryDto.getTicket());
        lottery.setAmount(lotteryDto.getAmount());
        lottery.setPrice(lotteryDto.getPrice());
        String ticket = lotteryService.addLottery(lottery);
        return new ResponseEntity<>(new ResponseLottery(ticket), HttpStatus.CREATED);
    }
}

record ResponseLottery(String ticket) {}
record ResponseLotteryList(List<String> tickets) {}
package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    LotteryService lotteryService;

    public AdminController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/lotteries")
    public ResponseLotteryList getLotteryList() {
        return new ResponseLotteryList(lotteryService.getLotteryList());
    }

    @PostMapping("/lotteries")
    public ResponseLottery addLottery(@Valid @RequestBody LotteryRequestDto lotteryDto) {
        Lottery lottery = new Lottery();
        lottery.setTicket(lotteryDto.getTicket());
        lottery.setAmount(lotteryDto.getAmount());
        lottery.setPrice(lotteryDto.getPrice());
        lotteryService.addLottery(lottery);
        return new ResponseLottery(lotteryDto.getTicket());
    }
}

record ResponseLottery(String ticket) {}

record ResponseLotteryList(List<String> tickets) {}
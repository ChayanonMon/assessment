package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LotteryService {
    public List<String> getLotteryList() {
        return new ArrayList<>(
                Arrays.asList("000001", "000002", "123456"));
    }

    public String addLottery(Lottery lottery) {
        return lottery.getTicket();
    }
}

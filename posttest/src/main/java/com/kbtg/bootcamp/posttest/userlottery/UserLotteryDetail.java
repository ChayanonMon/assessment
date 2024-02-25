package com.kbtg.bootcamp.posttest.userlottery;

import lombok.Getter;

import java.util.List;

@Getter
public class UserLotteryDetail {
    private final List<String> tickets;
    private final Integer count;
    private final Double cost;

    public UserLotteryDetail(List<String> tickets, Integer count , Double cost) {
        this.tickets = tickets;
        this.count = count;
        this.cost = cost;
    }
}

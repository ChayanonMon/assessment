package com.kbtg.bootcamp.posttest.userlottery;

import java.util.List;

public class UserLotteryDetail {
    private List<String> tickets;
    private Integer count;
    private Double cost;

    UserLotteryDetail(List<String> tickets, Integer count , Double cost) {
        this.tickets = tickets;
        this.count = count;
        this.cost = cost;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public Integer getCount() {
        return count;
    }

    public Double getCost() {
        return cost;
    }
}

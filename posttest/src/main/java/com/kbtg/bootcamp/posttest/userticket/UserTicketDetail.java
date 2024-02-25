package com.kbtg.bootcamp.posttest.userticket;

import lombok.Getter;

import java.util.List;

@Getter
public class UserTicketDetail {
    private final List<String> tickets;
    private final Integer count;
    private final Double cost;

    public UserTicketDetail(List<String> tickets, Integer count , Double cost) {
        this.tickets = tickets;
        this.count = count;
        this.cost = cost;
    }
}

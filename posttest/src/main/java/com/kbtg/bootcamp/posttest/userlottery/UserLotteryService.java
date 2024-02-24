package com.kbtg.bootcamp.posttest.userlottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;


@Component
public class UserLotteryService {

    public String buyUserLotteryByTicketId( Integer userId , String ticketId) {
        // TODO : Check if lottery exist in lottery table
        return "1";
    }

    public UserLotteryDetail getAllLotteryByUser(@PathVariable Integer userId) {
        List<String> tickets = new ArrayList<>(
                Arrays.asList("000001", "000002", "123456"));
        return new UserLotteryDetail(tickets, tickets.size(), 0.0);
    }

    public String deleteUserLottery( Integer userId , String ticketId) {
        return "012345";
    }
}

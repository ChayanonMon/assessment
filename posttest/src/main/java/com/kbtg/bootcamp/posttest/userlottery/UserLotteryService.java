package com.kbtg.bootcamp.posttest.userlottery;

import java.util.List;
import java.util.Optional;

import com.kbtg.bootcamp.posttest.exception.BadRequestException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.springframework.stereotype.Service;

@Service
public class UserLotteryService {
    UserLotteryRepository userLotteryRepository;
    LotteryRepository lotteryRepository;

    UserLotteryService(UserLotteryRepository userLotteryRepository, LotteryRepository lotteryRepository) {
        this.userLotteryRepository = userLotteryRepository;
        this.lotteryRepository = lotteryRepository;
    }

    public String buyUserLotteryByTicketId(String userId, String ticketId) throws Exception {
        Optional<UserLottery> optionalUserLottery = userLotteryRepository.findByUserIdAndTicket(userId, ticketId);
        if (optionalUserLottery.isPresent()) {
            return optionalUserLottery.get().getId().toString();
        }

        Optional<Lottery> optionalLottery = lotteryRepository.findByTicket(ticketId);
        if (optionalLottery.isEmpty()) {
            throw new BadRequestException("Ticket doest not existed");
        }

        UserLottery userLottery = new UserLottery(userId, ticketId);
        userLotteryRepository.save(userLottery);
        return userLottery.getId().toString();
    }

    public UserLotteryDetail getAllLotteryByUser(String userId) {
        List<UserLottery> userLotteryList = userLotteryRepository.findByUserId(userId);
        List<String> tickets = userLotteryList.stream().map(UserLottery::getTicket).toList();

        Double cost = 0.0;
        List<Lottery> lotteryList = lotteryRepository.findByTicketIn(tickets);
        for (Lottery lottery : lotteryList) {
            cost += lottery.getPrice();
        }

        return new UserLotteryDetail(tickets, tickets.size(), cost);
    }

    public String deleteUserLottery(String userId, String ticketId) {
        userLotteryRepository.deleteUserLotteryByTicket(userId, ticketId);
        return ticketId;
    }
}

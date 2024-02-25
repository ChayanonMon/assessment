package com.kbtg.bootcamp.posttest.userlottery;

import java.util.List;
import java.util.Optional;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserLotteryService {
    private final UserLotteryRepository userLotteryRepository;
    private final LotteryRepository lotteryRepository;

    public UserLotteryService(UserLotteryRepository userLotteryRepository, LotteryRepository lotteryRepository) {
        this.userLotteryRepository = userLotteryRepository;
        this.lotteryRepository = lotteryRepository;
    }

    @Transactional
    public String buyUserLotteryByTicketId(String userId, String ticketId) {
        Optional<UserLottery> optionalUserLottery = userLotteryRepository.findByUserIdAndTicket(userId, ticketId);
        return optionalUserLottery.map(userLottery -> userLottery.getId().toString())
                .orElseGet(() -> createAndSaveUserLottery(userId, ticketId));
    }

    public UserLotteryDetail getAllLotteryByUser(String userId) throws NotFoundException {
        List<UserLottery> userLotteryList = userLotteryRepository.findByUserId(userId);
        if (userLotteryList.isEmpty()) {
            throw new NotFoundException("User does not exist: " + userId);
        }
        List<String> tickets = userLotteryList.stream().map(UserLottery::getTicket).toList();
        double totalCost = calculateTotalCost(tickets);

        return new UserLotteryDetail(tickets, tickets.size(), totalCost);
    }

    @Transactional
    public String deleteUserLottery(String userId, String ticketId) {
        userLotteryRepository.deleteUserLotteryByTicket(userId, ticketId);
        return ticketId;
    }

    private String createAndSaveUserLottery(String userId, String ticketId) throws NotFoundException {
        Optional<Lottery> lottery = lotteryRepository.findByTicket(ticketId);
        if (lottery.isEmpty()) {
            throw new NotFoundException("Ticket does not exist: " + ticketId);
        }

        UserLottery userLottery = new UserLottery(userId, ticketId);
        userLotteryRepository.save(userLottery);
        return userLottery.getId().toString();
    }

    private Double calculateTotalCost(List<String> ticketIds) {
        return lotteryRepository.findByTicketIn(ticketIds).stream().mapToDouble(Lottery::getPrice).sum();
    }
}

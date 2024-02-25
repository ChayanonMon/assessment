package com.kbtg.bootcamp.posttest.userticket;

import java.util.List;
import java.util.Optional;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserTicketService {
    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;

    public UserTicketService(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }

    @Transactional
    public String buyUserTicketByTicketId(String userId, String ticketId) {
        Optional<UserTicket> optionalUserTicket = userTicketRepository.findByUserIdAndTicket(userId, ticketId);
        return optionalUserTicket.map(userTicket -> userTicket.getId().toString())
                .orElseGet(() -> createAndSaveUserTicket(userId, ticketId));
    }

    public UserTicketDetail getAllLotteryByUser(String userId) throws NotFoundException {
        List<UserTicket> userTicketList = userTicketRepository.findByUserId(userId);
        if (userTicketList.isEmpty()) {
            throw new NotFoundException("User does not exist: " + userId);
        }
        List<String> tickets = userTicketList.stream().map(UserTicket::getTicket).toList();
        double totalCost = calculateTotalCost(tickets);

        return new UserTicketDetail(tickets, tickets.size(), totalCost);
    }

    @Transactional
    public String deleteUserTicket(String userId, String ticketId) {
        userTicketRepository.deleteUserTicketByTicket(userId, ticketId);
        return ticketId;
    }

    private String createAndSaveUserTicket(String userId, String ticketId) throws NotFoundException {
        Optional<Lottery> lottery = lotteryRepository.findByTicket(ticketId);
        if (lottery.isEmpty()) {
            throw new NotFoundException("Ticket does not exist: " + ticketId);
        }

        UserTicket userTicket = new UserTicket(userId, ticketId);
        userTicketRepository.save(userTicket);
        return userTicket.getId().toString();
    }

    private Double calculateTotalCost(List<String> ticketIds) {
        return lotteryRepository.findByTicketIn(ticketIds).stream().mapToDouble(Lottery::getPrice).sum();
    }
}

package com.kbtg.bootcamp.posttest.lottery;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    @Autowired
    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public List<String> getLotteryList() {
        List<Lottery> lotteries = lotteryRepository.findAll();
        return lotteries.stream().map(Lottery::getTicket).toList();
    }

    @Transactional
    public String addLottery(Lottery lottery) throws Exception {
        Optional<Lottery> optionalLottery = lotteryRepository.findByTicket(lottery.getTicket());
        if (optionalLottery.isPresent()) {
            return optionalLottery.get().getTicket();
        }
        lotteryRepository.save(lottery);
        return lottery.getTicket();
    }
}

package com.kbtg.bootcamp.posttest.userlottery;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLotteryRepository extends JpaRepository<UserLottery, Long> {
    Optional<UserLottery> findByUserIdAndTicket(String userId ,String ticket);

    List<UserLottery> findByUserId(String userId);

    @Modifying
    @Query("DELETE FROM UserLottery ul WHERE ul.userId = ?1 AND ul.ticket = ?2")
    void deleteUserLotteryByTicket(String userId, String ticket);
}

package com.kbtg.bootcamp.posttest.userlottery;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLotteryRepository extends JpaRepository<UserLottery, Long> {
    Optional<UserLottery> findByTicket(String ticket);

    Optional<UserLottery> findByUserIdAndTicket(String userId ,String ticket);

    List<UserLottery> findByUserId(String userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_lottery WHERE user_id = ?1 AND ticket = ?2", nativeQuery = true)
    void deleteUserLotteryByTicket(String userId , String ticket);
}

package com.kbtg.bootcamp.posttest.userticket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    Optional<UserTicket> findByUserIdAndTicket(String userId , String ticket);

    List<UserTicket> findByUserId(String userId);

    @Modifying
    @Query("DELETE FROM UserTicket ul WHERE ul.userId = ?1 AND ul.ticket = ?2")
    void deleteUserTicketByTicket(String userId, String ticket);
}

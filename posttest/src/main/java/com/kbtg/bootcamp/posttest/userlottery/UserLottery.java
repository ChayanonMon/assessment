package com.kbtg.bootcamp.posttest.userlottery;

import jakarta.persistence.*;

@Entity
@Table(name = "user_lottery")
public class UserLottery {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userId;

    @Column(unique=true)
    private String ticket;

    public UserLottery(String userId , String ticket) {
        this.userId = userId;
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}

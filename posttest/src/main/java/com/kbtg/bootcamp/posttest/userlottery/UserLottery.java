package com.kbtg.bootcamp.posttest.userlottery;

import jakarta.persistence.*;

@Entity
@Table(name = "user_lottery")
public class UserLottery {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String userId;
    private String ticket;

    public UserLottery(String id , String ticket) {
        this.id = id;
        this.ticket = ticket;
    }

    public String getId() {
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

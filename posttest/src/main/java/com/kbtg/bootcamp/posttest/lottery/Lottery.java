package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "lottery")
public class Lottery {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "lottery_ticket", length = 6)
    @Pattern(regexp = "\\d{6}")
    private String ticket;
    private Double price;
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

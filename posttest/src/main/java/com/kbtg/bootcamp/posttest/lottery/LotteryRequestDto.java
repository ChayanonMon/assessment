package com.kbtg.bootcamp.posttest.lottery;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LotteryRequestDto {
    @NotNull
    @Size(min = 6, max = 6, message = "Lottery ticket id length should be 6 characters")
    private String ticket;

    @NotNull
    @Range(min = 0, message = "Lottery price should not less than zero")
    private Double price;

    @NotNull
    @Range(min = 0, message = "Lottery amount should not less than zero")
    private Integer amount;

    LotteryRequestDto(String ticket, Double price , Integer amount) {
        this.ticket = ticket;
        this.price = price;
        this.amount = amount;
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

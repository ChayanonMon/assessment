package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lottery")
public class Lottery {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 6, nullable = false)
    @Pattern(regexp = "\\d{6}", message = "Ticket must be exactly 6 digits")
    private String ticket;

    @Column(nullable = false)
    @Range(min = 0, message = "Lottery price must not be less than zero")
    private Double price;

    @Column(nullable = false)
    @Range(min = 0, message = "Lottery amount must not be less than zero")
    private Integer amount;


    public Lottery(String ticket , Double price , Integer amount ) {
        this.ticket = ticket;
        this.price = price;
        this.amount = amount;
    }
}

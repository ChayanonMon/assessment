package com.kbtg.bootcamp.posttest.userlottery;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_lottery")
public class UserLottery {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10, nullable = false)
    @Pattern(regexp = "\\d{10}", message = "UserId must be exactly 10 digits")
    private String userId;

    @Column(length = 6, nullable = false)
    @Pattern(regexp = "\\d{6}", message = "Ticket must be exactly 6 digits")
    private String ticket;

    public UserLottery(String userId , String ticket) {
        this.userId = userId;
        this.ticket = ticket;
    }
}

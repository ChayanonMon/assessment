package com.kbtg.bootcamp.posttest.lottery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class LotteryRequestDto {
    @NotNull(message = "Lottery ticket ID is required")
    @Pattern(regexp = "\\d{6}", message = "Lottery ticket ID must be exactly 6 digits long")
    private String ticket;

    @NotNull(message = "Lottery price is required")
    @Range(min = 0, message = "Lottery price must not be less than zero")
    private Double price;

    @NotNull(message = "Lottery amount is required")
    @Range(min = 0, message = "Lottery amount must not be less than zero")
    private Integer amount;
}

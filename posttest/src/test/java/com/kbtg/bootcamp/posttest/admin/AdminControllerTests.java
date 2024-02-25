package com.kbtg.bootcamp.posttest.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTests {
    private MockMvc mockMvc;

    @Mock
    private LotteryService lotteryService;

    @InjectMocks
    private AdminController adminController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(adminController).build();
    }

    @Test
    @DisplayName("when perform on GET: /admin/lotteries should return status(200)")
    void testGetLotteryList() throws Exception {
        when(lotteryService.getLotteryList()).thenReturn(Arrays.asList("012345", "567890"));

        mockMvc.perform(get("/admin/lotteries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets").isArray())
                .andExpect(jsonPath("$.tickets[0]").value("012345"))
                .andExpect(jsonPath("$.tickets[1]").value("567890"));
    }

    @Test
    @DisplayName("when perform on POST: /admin/lotteries should return status(201)")
    void testAddLottery() throws Exception {
        LotteryRequestDto lotteryDto = new LotteryRequestDto("123456", 100.0, 10);

        when(lotteryService.addLottery(any(Lottery.class))).thenReturn("123456");

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lotteryDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticket").value("123456"));
    }
}

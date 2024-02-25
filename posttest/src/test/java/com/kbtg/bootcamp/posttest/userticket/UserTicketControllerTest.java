package com.kbtg.bootcamp.posttest.userticket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserTicketControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserTicketService userTicketService;

    @InjectMocks
    private UserTicketController userTicketController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userTicketController).build();
    }

    @Test
    @DisplayName("when perform on POST: /users/1234567890/lotteries/123456 should return status(201)")
    public void testBuyUserTicketByTicketId() throws Exception {
        when(userTicketService.buyUserTicketByTicketId(anyString(), anyString())).thenReturn("1");

        mockMvc.perform(post("/users/1234567890/lotteries/123456"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    @DisplayName("when perform on GET: /users/1234567890/lotteries should return status(200)")
    public void testGetAllUserTicketByUser() throws Exception {
        when(userTicketService.getAllLotteryByUser(anyString())).thenReturn(new UserTicketDetail(Arrays.asList("123456", "567890"), 2, 100.0));

        mockMvc.perform(get("/users/1234567890/lotteries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets").isArray())
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.cost").value(100.0));
    }

    @Test
    @DisplayName("when perform on DELETE: /users/1234567890/lotteries/567890 should return status(200)")
    public void testDeleteUserTicket() throws Exception {
        when(userTicketService.deleteUserTicket(anyString(), anyString())).thenReturn("567890");

        mockMvc.perform(delete("/users/1234567890/lotteries/567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket").value("567890"));
    }
}

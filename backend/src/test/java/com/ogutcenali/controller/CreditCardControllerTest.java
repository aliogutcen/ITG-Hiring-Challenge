package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateCreditCardRequest;
import com.ogutcenali.dto.response.CreditCardResponse;
import com.ogutcenali.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

public class CreditCardControllerTest {

    @Mock
    private CreditCardService creditCardService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        CreditCardController creditCardController = new CreditCardController(creditCardService);
        mockMvc = MockMvcBuilders.standaloneSetup(creditCardController).build();
    }

    @Test
    public void testAddCard() throws Exception {
        when(creditCardService.createCreditCard(any(CreateCreditCardRequest.class))).thenReturn(true);
        mockMvc.perform(post("/api/v1/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cardNumber\":\"1234567812345678\"}"))
                .andExpect(status().isOk());

        verify(creditCardService, times(1)).createCreditCard(any(CreateCreditCardRequest.class));
    }
    @Test
    public void testGetAllCreditCardForUser() throws Exception {
        when(creditCardService.getAllCreditCardsForUser(anyInt())).thenReturn(Collections.singletonList(new CreditCardResponse()));
        mockMvc.perform(get("/api/v1/cards/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(creditCardService, times(1)).getAllCreditCardsForUser(anyInt());
    }
    @Test
    public void testDeleteCard() throws Exception {
        when(creditCardService.deleteCard(anyLong())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/cards/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(creditCardService, times(1)).deleteCard(anyLong());
    }
}

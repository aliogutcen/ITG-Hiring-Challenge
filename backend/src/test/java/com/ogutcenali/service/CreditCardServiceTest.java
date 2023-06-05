package com.ogutcenali.service;

import com.ogutcenali.dto.request.CreateCreditCardRequest;
import com.ogutcenali.model.CreditCard;
import com.ogutcenali.repository.CreditCardRepository;
import com.ogutcenali.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreditCardServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    private CreditCardService creditCardService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        creditCardService = new CreditCardService(creditCardRepository);
    }

    @Test
    public void testCreateCreditCard() {
        CreateCreditCardRequest request = new CreateCreditCardRequest();
        request.setCardNumber("1234567890");
        when(creditCardRepository.findByCardNumber(anyString())).thenReturn(Optional.empty());
        assertTrue(creditCardService.createCreditCard(request));
        verify(creditCardRepository, times(1)).save(any(CreditCard.class));
    }

    @Test
    public void testDeleteCard() {
        Long id = 1L;
        CreditCard creditCard = new CreditCard();
        when(creditCardRepository.findById(anyLong())).thenReturn(Optional.of(creditCard));
        assertTrue(creditCardService.deleteCard(id));
        verify(creditCardRepository, times(1)).delete(creditCard);
    }

    @Test
    public void testGetAllCreditCardsForUser() {
        Integer userId = 1;
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("1234567890");
        when(creditCardRepository.findByUserId(anyInt())).thenReturn(Collections.singletonList(creditCard));
        assertFalse(creditCardService.getAllCreditCardsForUser(userId).isEmpty());
        verify(creditCardRepository, times(1)).findByUserId(userId);
    }
}

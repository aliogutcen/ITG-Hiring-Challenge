package com.ogutcenali.service;

import com.ogutcenali.config.security.JwtService;
import com.ogutcenali.dto.response.CreditCardResponse;
import com.ogutcenali.dto.response.OrdersResponse;
import com.ogutcenali.dto.response.UserProfileResponse;
import com.ogutcenali.model.User;
import com.ogutcenali.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private CreditCardService creditCardService;

    @Mock
    private OrderService orderService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, jwtService, creditCardService, orderService);
    }

    @Test
    void testGetUserProfile() {
        // Arrange
        Integer userId = 1;
        User user = new User();
        user.setId(userId);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john.doe@example.com");



        when(userRepository.findById(userId)).thenReturn(Optional.of(user));


        // Act
        UserProfileResponse userProfile = userService.getUserProfile(userId);

        // Assert
        assertEquals(user.getFirstname(), userProfile.getFirstname());
        assertEquals(user.getLastname(), userProfile.getLastname());
        assertEquals(user.getEmail(), userProfile.getEmail());


        verify(userRepository, times(1)).findById(userId);
        verify(creditCardService, times(1)).getAllCreditCardsForUser(userId);
    }

    @Test
    void testGetOrdersForUser() {
        // Arrange
        Integer userId = 1;
   ;
        // Act
        List<OrdersResponse> result = userService.getOrdersForUser(userId);



        verify(orderService, times(1)).getOrdersForUser(userId);
    }
}

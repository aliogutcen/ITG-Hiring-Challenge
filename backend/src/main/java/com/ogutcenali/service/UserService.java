package com.ogutcenali.service;

import com.ogutcenali.config.security.JwtService;
import com.ogutcenali.dto.response.CreditCardResponse;
import com.ogutcenali.dto.response.OrdersResponse;
import com.ogutcenali.dto.response.UserProfileResponse;
import com.ogutcenali.model.User;
import com.ogutcenali.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CreditCardService creditCardService;
    private final OrderService orderService;

    public UserService(UserRepository userRepository, JwtService jwtService, CreditCardService creditCardService, OrderService orderService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.creditCardService = creditCardService;
        this.orderService = orderService;
    }

    public UserProfileResponse getUserProfile(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        List<CreditCardResponse> creditCard = creditCardService.getAllCreditCardsForUser(user.get().getId());
        return UserProfileResponse.builder()
                .creditCardNumber(creditCard)
                .lastname(user.get().getLastname())
                .firstname(user.get().getFirstname())
                .email(user.get().getEmail())
                .build();

    }

    public List<OrdersResponse> getOrdersForUser(Integer id) {
        return orderService.getOrdersForUser(id);
    }
}

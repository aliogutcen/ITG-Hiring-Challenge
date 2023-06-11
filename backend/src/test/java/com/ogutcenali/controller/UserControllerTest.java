package com.ogutcenali.controller;

import com.ogutcenali.dto.response.OrdersResponse;
import com.ogutcenali.dto.response.UserProfileResponse;
import com.ogutcenali.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserProfileTest() {
        Integer id = 1;
        UserProfileResponse responseMock = new UserProfileResponse();
        //... Initialize the responseMock as needed

        when(userService.getUserProfile(id)).thenReturn(responseMock);

        ResponseEntity<UserProfileResponse> response = userController.getUserProfile(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseMock, response.getBody());
        verify(userService, times(1)).getUserProfile(id);
    }

    @Test
    public void getOrdersForUserTest() {
        Integer id = 1;
        List<OrdersResponse> responseMock = Arrays.asList(new OrdersResponse(), new OrdersResponse());
        //... Initialize the responseMock as needed

        when(userService.getOrdersForUser(id)).thenReturn(responseMock);

        ResponseEntity<List<OrdersResponse>> response = userController.getOrdersForUser(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseMock.size(), response.getBody().size());
        verify(userService, times(1)).getOrdersForUser(id);
    }
}

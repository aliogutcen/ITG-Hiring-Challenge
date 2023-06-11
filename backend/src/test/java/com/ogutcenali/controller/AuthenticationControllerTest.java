package com.ogutcenali.controller;

import com.ogutcenali.dto.request.AuthenticationRequest;
import com.ogutcenali.dto.request.RegisterRequest;
import com.ogutcenali.dto.response.AuthenticationResponse;
import com.ogutcenali.service.AuthenticationService;
import com.ogutcenali.service.VerificationUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    VerificationUserService verificationUserService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registerTest() {
        RegisterRequest request = new RegisterRequest();
        // ... initialize request as needed

        AuthenticationResponse mockResponse = new AuthenticationResponse();
        // ... initialize mockResponse as needed
        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(mockResponse);

        ResponseEntity<Void> response = authenticationController.register(request);

        assertEquals(201, response.getStatusCodeValue());
        verify(authenticationService, times(1)).register(any(RegisterRequest.class));
    }

    @Test
    public void authenticateTest() {
        AuthenticationRequest request = new AuthenticationRequest();
        // ... initialize request as needed
        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(new AuthenticationResponse());

        ResponseEntity<AuthenticationResponse> response = authenticationController.authenticate(request);

        assertEquals(200, response.getStatusCodeValue());
        verify(authenticationService, times(1)).authenticate(any(AuthenticationRequest.class));
    }

    @Test
    public void confirmUserAccountTest() {
        String token = "testToken";
        when(verificationUserService.confirmEmail(anyString())).thenReturn("Confirmation successful");

        ResponseEntity<?> response = authenticationController.confirmUserAccount(token);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Confirmation successful", response.getBody());
        verify(verificationUserService, times(1)).confirmEmail(anyString());
    }
}

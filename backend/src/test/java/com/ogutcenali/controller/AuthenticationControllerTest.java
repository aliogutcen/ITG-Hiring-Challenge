package com.ogutcenali.controller;

import com.ogutcenali.dto.request.AuthenticationRequest;
import com.ogutcenali.dto.request.RegisterRequest;
import com.ogutcenali.dto.response.AuthenticationResponse;
import com.ogutcenali.service.AuthenticationService;
import com.ogutcenali.service.VerificationUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private VerificationUserService verificationUserService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        AuthenticationController authenticationController = new AuthenticationController(authenticationService, verificationUserService);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void testRegister() throws Exception {
        when(authenticationService.register(any(RegisterRequest.class)))
                .thenReturn(new AuthenticationResponse("Bearer some_jwt_token"));

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test\",\"password\":\"testPassword\"}")) // Use an example request payload
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).register(any(RegisterRequest.class));
    }

    @Test
    public void testAuthenticate() throws Exception {
        when(authenticationService.authenticate(any(AuthenticationRequest.class)))
                .thenReturn(new AuthenticationResponse("Bearer some_jwt_token"));

        mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test\",\"password\":\"testPassword\"}")) // Use an example request payload
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).authenticate(any(AuthenticationRequest.class));
    }

    @Test
    public void testConfirmUserAccount() throws Exception {


        mockMvc.perform(get("/api/v1/auth/confirm-account?token=some_token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(verificationUserService, times(1)).confirmEmail(anyString());
    }
}

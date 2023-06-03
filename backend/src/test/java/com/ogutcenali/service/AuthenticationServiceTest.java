package com.ogutcenali.service;

import com.ogutcenali.config.security.JwtService;
import com.ogutcenali.dto.request.AuthenticationRequest;
import com.ogutcenali.dto.request.RegisterRequest;
import com.ogutcenali.model.User;
import com.ogutcenali.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private VerificationUserService verificationUserService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("generatedToken");

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@mail.com");
        registerRequest.setPassword("password");

        authenticationService.register(registerRequest);

        verify(userRepository, times(1)).save(any(User.class));
        verify(verificationUserService, times(1)).save(any(User.class));
    }

    @Test
    void testAuthenticate() {
        User user = new User();
        user.setEnabled(true);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(User.class))).thenReturn("generatedToken");

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("test@mail.com");
        authenticationRequest.setPassword("password");

        authenticationService.authenticate(authenticationRequest);

        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test
    public void testActiveCustomer() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("encodedPassword");
        user.setEnabled(false);

        authenticationService.activeCustomer(user);

        verify(userRepository, times(1)).save(user);
        assert(user.isEnabled());
    }
}

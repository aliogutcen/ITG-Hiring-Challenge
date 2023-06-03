package com.ogutcenali.service;

import com.ogutcenali.model.User;
import com.ogutcenali.model.VerificationUser;
import com.ogutcenali.repository.VerificationRepository;
import com.ogutcenali.utility.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VerificationUserServiceTest {

    private VerificationUserService verificationUserService;
    @Mock
    private VerificationRepository verificationRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        verificationUserService = new VerificationUserService(verificationRepository, emailService, authenticationService);
    }
    @Test
    void findByToken() {
        String token = "test-token";
        VerificationUser expectedUser = new VerificationUser();
        when(verificationRepository.findByToken(token)).thenReturn(expectedUser);
        VerificationUser actualUser = verificationUserService.findByToken(token);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findByUser() {
        User user = new User();
        VerificationUser expectedUser = new VerificationUser();
        when(verificationRepository.findByUser(user)).thenReturn(expectedUser);
        VerificationUser actualUser = verificationUserService.findByUser(user);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void save() throws MessagingException {
        User user = new User();
        VerificationUser expectedUser = new VerificationUser();
        expectedUser.setUser(user);
        expectedUser.setToken("test-token");
        doNothing().when(emailService).sendMail(user);
        when(verificationRepository.save(expectedUser)).thenReturn(expectedUser);
        verificationUserService.save(user);
        verify(verificationRepository, times(1)).save(any(VerificationUser.class));
        verify(emailService, times(1)).sendMail(user);
    }

    @Test
    void confirmEmail() {
        String confirmationToken = "test-token";
        VerificationUser verificationUser = new VerificationUser();
        User user = new User();
        verificationUser.setUser(user);
        when(verificationRepository.findByToken(confirmationToken)).thenReturn(verificationUser);
        doNothing().when(authenticationService).activeCustomer(user);
        String result = verificationUserService.confirmEmail(confirmationToken);
        assertEquals("Verify user", result);
    }

    @Test
    void confirmEmail_InvalidToken() {
        String invalidToken = "invalid-token";
        when(verificationRepository.findByToken(invalidToken)).thenReturn(null);
        String result = verificationUserService.confirmEmail(invalidToken);
        assertEquals("Error: Invalid confirmation token", result);
    }
}

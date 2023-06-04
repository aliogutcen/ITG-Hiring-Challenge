package com.ogutcenali.service;

import com.ogutcenali.model.FailedAttempt;
import com.ogutcenali.repository.FailedAttemptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FailedAttemptServiceTest {

    @Mock
    private FailedAttemptRepository failedAttemptRepository;
    @Mock
    private AuthenticationService authenticationService;
    private FailedAttemptService failedAttemptService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.failedAttemptService = new FailedAttemptService(failedAttemptRepository, authenticationService);
    }

    @Test
    public void testCreateFailedAttemptForUser() {
        Integer userId = 1;
        String email = "test@example.com";

        failedAttemptService.createFailedAttemptForUser(userId, email);

        verify(failedAttemptRepository, times(1)).save(any(FailedAttempt.class));
    }

    @Test
    public void testRecordFailedAttemptWhenAttemptDoesNotExist() {
        String testEmail = "testEmail@example.com";

        when(failedAttemptRepository.findByEmail(testEmail)).thenReturn(Optional.empty());

        boolean result = failedAttemptService.recordFailedAttempt(testEmail);

        assertTrue(result, "The recordFailedAttempt should return true");
        verify(failedAttemptRepository, times(1)).findByEmail(testEmail);
        verify(failedAttemptRepository, times(1)).save(any(FailedAttempt.class));
    }

    @Test
    public void testRecordFailedAttemptWhenAttemptShouldReset() {
        String testEmail = "testEmail@example.com";
        FailedAttempt failedAttempt = new FailedAttempt();
        failedAttempt.setEmail(testEmail);
        failedAttempt.setFailedAttempts(2);
        failedAttempt.setLastFailedAttempt(LocalDateTime.now().minusHours(2));

        when(failedAttemptRepository.findByEmail(testEmail)).thenReturn(Optional.of(failedAttempt));

        boolean result = failedAttemptService.recordFailedAttempt(testEmail);

        assertTrue(result, "The recordFailedAttempt should return true as the failed attempts should reset");
        verify(failedAttemptRepository, times(1)).findByEmail(testEmail);
        verify(failedAttemptRepository, times(1)).save(any(FailedAttempt.class));
    }

    @Test
    public void testRecordFailedAttemptWhenAttemptShouldLock() {
        String testEmail = "testEmail@example.com";
        FailedAttempt failedAttempt = new FailedAttempt();
        failedAttempt.setEmail(testEmail);
        failedAttempt.setFailedAttempts(4);
        failedAttempt.setLastFailedAttempt(LocalDateTime.now());

        when(failedAttemptRepository.findByEmail(testEmail)).thenReturn(Optional.of(failedAttempt));

        boolean result = failedAttemptService.recordFailedAttempt(testEmail);

        assertFalse(result, "The recordFailedAttempt should return false as the failed attempts reached the limit");
        verify(failedAttemptRepository, times(1)).findByEmail(testEmail);
        verify(authenticationService, times(1)).customerLocked(testEmail);
        verify(failedAttemptRepository, times(1)).save(any(FailedAttempt.class));
    }

    @Test
    public void testRecordFailedAttemptWhenAttemptShouldNotLock() {
        String testEmail = "testEmail@example.com";
        FailedAttempt failedAttempt = new FailedAttempt();
        failedAttempt.setEmail(testEmail);
        failedAttempt.setFailedAttempts(2);
        failedAttempt.setLastFailedAttempt(LocalDateTime.now());

        when(failedAttemptRepository.findByEmail(testEmail)).thenReturn(Optional.of(failedAttempt));

        boolean result = failedAttemptService.recordFailedAttempt(testEmail);

        assertTrue(result, "The recordFailedAttempt should return true as the failed attempts are not at the limit yet");
        verify(failedAttemptRepository, times(1)).findByEmail(testEmail);
        verify(failedAttemptRepository, times(1)).save(any(FailedAttempt.class));
    }
}

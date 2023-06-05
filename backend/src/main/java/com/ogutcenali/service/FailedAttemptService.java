package com.ogutcenali.service;

import com.ogutcenali.model.FailedAttempt;
import com.ogutcenali.repository.FailedAttemptRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class FailedAttemptService {

    private final FailedAttemptRepository failedAttemptRepository;
    private final AuthenticationService authenticationService;

    public FailedAttemptService(FailedAttemptRepository failedAttemptRepository,@Lazy AuthenticationService authenticationService) {
        this.failedAttemptRepository = failedAttemptRepository;
        this.authenticationService = authenticationService;
    }

    public void createFailedAttemptForUser(Integer userId, String email) {
        FailedAttempt failedAttempt = FailedAttempt.builder()
                .failedAttempts(0)
                .userId(userId)
                .email(email)
                .build();
        saveFailedAttempt(failedAttempt);
    }

    public boolean recordFailedAttempt(String email) {
        FailedAttempt failedAttempt = failedAttemptRepository.findByEmail(email)
                .orElseGet(() -> createAndReturnFailedAttempt(email));
        return processFailedAttempt(failedAttempt, email);
    }

    private FailedAttempt createAndReturnFailedAttempt(String email) {
        FailedAttempt failedAttempt = new FailedAttempt();
        failedAttempt.setEmail(email);
        failedAttempt.setFailedAttempts(0);
        failedAttempt.setLastFailedAttempt(LocalDateTime.now());
        return failedAttempt;
    }

    private boolean processFailedAttempt(FailedAttempt failedAttempt, String email) {
        if (shouldResetFailedAttempts(failedAttempt)) {
            resetFailedAttempts(failedAttempt);
            return true;
        }
        if (shouldLockCustomer(failedAttempt)) {
            lockCustomerAndRecordFailure(failedAttempt, email);
            return false;
        }
        recordFailure(failedAttempt);
        return true;
    }

    private boolean shouldResetFailedAttempts(FailedAttempt failedAttempt) {
        LocalDateTime lastFailedAttempt = failedAttempt.getLastFailedAttempt();
        return lastFailedAttempt == null || lastFailedAttempt.plusHours(1).isBefore(LocalDateTime.now());
    }

    private void resetFailedAttempts(FailedAttempt failedAttempt) {
        failedAttempt.setFailedAttempts(0);
        updateFailedAttempt(failedAttempt);
    }

    private boolean shouldLockCustomer(FailedAttempt failedAttempt) {
        return failedAttempt.getFailedAttempts() + 1 >= 5;
    }

    private void lockCustomerAndRecordFailure(FailedAttempt failedAttempt, String email) {
        authenticationService.customerLocked(email);
        recordFailure(failedAttempt);
    }

    private void recordFailure(FailedAttempt failedAttempt) {
        failedAttempt.setFailedAttempts(failedAttempt.getFailedAttempts() + 1);
        updateFailedAttempt(failedAttempt);
    }

    private void saveFailedAttempt(FailedAttempt failedAttempt) {
        failedAttempt.setLastFailedAttempt(LocalDateTime.now());
        failedAttemptRepository.save(failedAttempt);
    }

    private void updateFailedAttempt(FailedAttempt failedAttempt) {
        failedAttempt.setLastFailedAttempt(LocalDateTime.now());
        failedAttemptRepository.save(failedAttempt);
    }
}


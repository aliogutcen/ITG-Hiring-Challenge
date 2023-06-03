package com.ogutcenali.service;

import com.ogutcenali.model.User;
import com.ogutcenali.model.VerificationUser;
import com.ogutcenali.repository.VerificationRepository;
import com.ogutcenali.utility.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.MessagingException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationUserService {

    private final VerificationRepository verificationRepository;
    private final EmailService emailService;
    private final AuthenticationService authenticationService;

    @Transactional
    public VerificationUser findByToken(String token) {
        return verificationRepository.findByToken(token);
    }

    @Transactional
    public VerificationUser findByUser(User user) {
        return verificationRepository.findByUser(user);
    }

    @Transactional
    public void save(User user) {
        String token = generateToken();
        VerificationUser verificationUser = VerificationUser.builder().user(user).token(token).build();
        verificationRepository.save(verificationUser);
        try {
            emailService.sendMail(user);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    public String confirmEmail(String confirmationToken) {
        VerificationUser verificationUser = findByToken(confirmationToken);
        if (verificationUser != null) {
            authenticationService.activeCustomer(verificationUser.getUser());
            return "Verify user";
        }
        return "Error: Invalid confirmation token";
    }
}


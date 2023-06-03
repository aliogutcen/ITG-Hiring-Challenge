package com.ogutcenali.utility;

import com.ogutcenali.model.User;
import com.ogutcenali.model.VerificationUser;
import com.ogutcenali.service.VerificationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final VerificationUserService verificationUserService;
    private JavaMailSender javaMailSender;

    public EmailService(@Lazy VerificationUserService verificationUserService, JavaMailSender javaMailSender) {
        this.verificationUserService = verificationUserService;
        this.javaMailSender = javaMailSender;
    }
    public void sendMail(User user) throws MessagingException {
        VerificationUser verificationUser = verificationUserService.findByUser(user);
        if (verificationUser != null) {
            String token = verificationUser.getToken();

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Complete Registration!");
            helper.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/api/v1/auth/confirm-account?token=" + token);
            javaMailSender.send(message);
        }
    }


}

package com.ogutcenali.service;

import com.ogutcenali.config.security.JwtService;
import com.ogutcenali.dto.request.AuthenticationRequest;
import com.ogutcenali.dto.request.RegisterRequest;
import com.ogutcenali.dto.response.AuthenticationResponse;
import com.ogutcenali.exception.AuthException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.mapper.IAuthenticationMapper;
import com.ogutcenali.model.User;
import com.ogutcenali.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final VerificationUserService verificationUserService;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService
            , AuthenticationManager authenticationManager, @Lazy VerificationUserService verificationUserService)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.verificationUserService = verificationUserService;
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        checkUserExists(registerRequest.getEmail());
        User user = createUser(registerRequest);
        saveUserAndVerification(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private void checkUserExists(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) throw new AuthException(EErrorType.AUTH_EMAIL_ERROR);
    }

    private User createUser(RegisterRequest registerRequest) {
        User user = IAuthenticationMapper.INSTANCE.toUserRegister(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(false);
        return user;
    }

    private void saveUserAndVerification(User user) {
        userRepository.save(user);
        verificationUserService.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        User user = findUserByEmail(authenticationRequest.getEmail());
        authenticateUser(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
    private User findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) throw new AuthException(EErrorType.USER_NOT_FOUND);
        User user = userOptional.get();
        if (!user.isEnabled()) throw new AuthException(EErrorType.ACCOUNT_NOT_ACTIVE);
        return user;
    }
    private void authenticateUser(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
        }
    }
    public void activeCustomer(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
}

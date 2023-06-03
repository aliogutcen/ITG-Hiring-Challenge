package com.ogutcenali.repository;

import com.ogutcenali.model.User;
import com.ogutcenali.model.VerificationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("verificationUserRepository")
public interface VerificationRepository extends JpaRepository<VerificationUser,Long> {

    VerificationUser findByToken(String token);
    VerificationUser findByUser(User user);

}

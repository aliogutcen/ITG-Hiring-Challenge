package com.ogutcenali.repository;

import com.ogutcenali.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    Optional<CreditCard> findByCardNumber(String cardNumber);
    List<CreditCard> findByUserId(Integer id);


}

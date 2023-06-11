package com.ogutcenali.service;

import com.ogutcenali.dto.request.CreateCreditCardRequest;
import com.ogutcenali.dto.response.CreditCardResponse;
import com.ogutcenali.exception.AuthException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.mapper.ICreditCardMapper;
import com.ogutcenali.model.CreditCard;
import com.ogutcenali.repository.CreditCardRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Transactional
    public Boolean createCreditCard(CreateCreditCardRequest createCreditCardRequest) {
        String cardNumber = createCreditCardRequest.getCardNumber();
        checkIfCardAlreadyExists(cardNumber);
        CreditCard creditCard = createCreditCardFromRequest(createCreditCardRequest);
        creditCardRepository.save(creditCard);
        return true;
    }

    private void checkIfCardAlreadyExists(String cardNumber) {
        Optional<CreditCard> existingCard = creditCardRepository.findByCardNumber(cardNumber);
        if (existingCard.isPresent()) {
            throw new AuthException(EErrorType.CREDITCARD_HAS_BEEN);
        }
    }

    private CreditCard createCreditCardFromRequest(CreateCreditCardRequest createCreditCardRequest) {
        return ICreditCardMapper.INSTANCE.toCreditCard(createCreditCardRequest);
    }

    @Transactional
    public boolean deleteCard(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No credit card found with id: " + id));
        creditCardRepository.delete(creditCard);
        return true;
    }

    @Cacheable(value = "creditcard", key = "#id")
    public List<CreditCardResponse> getAllCreditCardsForUser(Integer id) {
        List<CreditCard> creditCards = creditCardRepository.findByUserId(id);
        if (creditCards.isEmpty()) {
            return null;
        }
        return creditCards.stream()
                .map(creditCard -> CreditCardResponse.builder()
                        .cardNumber(creditCard.getCardNumber())
                        .build())
                .collect(Collectors.toList());
    }

}

package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateCreditCardRequest;
import com.ogutcenali.dto.response.CreditCardResponse;
import com.ogutcenali.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CreditCardController {

    private final CreditCardService creditCardService;
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>  addCard(@RequestBody CreateCreditCardRequest createCreditCardRequest){
        return ResponseEntity.ok(creditCardService.createCreditCard(createCreditCardRequest));
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<List<CreditCardResponse>> getAllCreditCardForUser(@PathVariable Integer id){
        return ResponseEntity.ok(creditCardService.getAllCreditCardsForUser(id));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteCard(@PathVariable Long id){
        return ResponseEntity.ok(creditCardService.deleteCard(id));
    }
}

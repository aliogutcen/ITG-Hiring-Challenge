package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateCreditCardRequest;
import com.ogutcenali.dto.response.CreditCardResponse;
import com.ogutcenali.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CreditCardController {

    private final CreditCardService creditCardService;
    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?>  addCard(@RequestBody CreateCreditCardRequest createCreditCardRequest){
        return ResponseEntity.ok(creditCardService.createCreditCard(createCreditCardRequest));
    }
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<CreditCardResponse>> getAllCreditCardForUser(@PathVariable Integer id){
        return ResponseEntity.ok(creditCardService.getAllCreditCardsForUser(id));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> deleteCard(@PathVariable Long id){
        return ResponseEntity.ok(creditCardService.deleteCard(id));
    }
}

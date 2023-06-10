package com.ogutcenali.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileResponse implements Serializable {
    private String firstname;

    private String lastname;

    private String email;
    private List<CreditCardResponse> creditCardNumber;
}

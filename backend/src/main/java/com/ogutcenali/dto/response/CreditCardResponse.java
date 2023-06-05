package com.ogutcenali.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditCardResponse implements Serializable {

    private String cardNumber;
    private Integer userId;
}

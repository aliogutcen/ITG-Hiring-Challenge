package com.ogutcenali.dto.request;

import com.ogutcenali.model.User;
import lombok.*;

import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateCreditCardRequest implements Serializable {
    private String cardNumber;

    private Integer userId;
}

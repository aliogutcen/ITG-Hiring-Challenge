package com.ogutcenali.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {


    @Email(message = "hatalı mail kardeş")
    private String email;

    @Size(min = 5)
    private String password;
}

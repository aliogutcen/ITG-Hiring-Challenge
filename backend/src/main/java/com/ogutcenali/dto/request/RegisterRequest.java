package com.ogutcenali.dto.request;

import com.ogutcenali.model.enums.Roles;
import lombok.*;

import javax.validation.constraints.Email;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String firstname;

    private String lastname;

    @Email(message = "Enter a valid email")
    private String email;

    private String password;

    private Roles role;
}

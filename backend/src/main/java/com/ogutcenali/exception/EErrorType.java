package com.ogutcenali.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    BAD_REQUEST_ERROR(1201,"Invalid Parameter Entry",BAD_REQUEST),
    AUTH_PASSWORD_ERROR(1301,"The passwords don't match.",BAD_REQUEST),
    AUTH_EMAIL_ERROR(1302,"This email has been used before.",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Sunucuda beklenmeyen hata",INTERNAL_SERVER_ERROR),
    KULLANICI_BULUNAMADI(2301,"No user with the id you are looking for was found",INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(4001,"INVALID TOKEN INFORMATION",BAD_REQUEST),
    AUTH_LOGIN_ERROR(4002,"USERNAME OR PASSWORD IS INCORRECT." ,INTERNAL_SERVER_ERROR ),
    ACCOUNT_NOT_ACTIVE(4003,"Your account is not active",BAD_REQUEST ),
    ACCOUNT_LOCKED(4005,"Your account is locked",BAD_REQUEST ),
    CATEGORY_HAS_BEEN(4015,"Category is been",BAD_REQUEST),
    CATEGORY_NOT_FOUND(4015,"Category not found",BAD_REQUEST),
    CREDITCARD_HAS_BEEN(4025,"Credit card is been",BAD_REQUEST),
    USER_NOT_FOUND
    ;

    private int code;

    private String message;

    private HttpStatus httpStatus;
}

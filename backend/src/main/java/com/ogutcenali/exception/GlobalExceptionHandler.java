package com.ogutcenali.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import static com.ogutcenali.exception.EErrorType.BAD_REQUEST_ERROR;
import static com.ogutcenali.exception.EErrorType.INTERNAL_ERROR;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception){
        EErrorType EErrorType = INTERNAL_ERROR;
        return new ResponseEntity<>(createError(EErrorType,exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleSpringMonoException(AuthException exception){
        return new ResponseEntity<>(createError(exception.getErrorType(),exception),exception.getErrorType().getHttpStatus());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        EErrorType EErrorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(EErrorType, exception), EErrorType.getHttpStatus());
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {
        EErrorType EErrorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(EErrorType, exception), EErrorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {
        EErrorType EErrorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createError(EErrorType, exception), EErrorType.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        EErrorType EErrorType = BAD_REQUEST_ERROR;
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors()
                .forEach(e -> fields.add(e.getField() + ": " + e.getDefaultMessage()));
        ErrorMessage errorMessage = createError(EErrorType, exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, EErrorType.getHttpStatus());
    }

    private ErrorMessage createError(EErrorType EErrorType, Exception exception){
        return ErrorMessage.builder()
                .code(EErrorType.getCode())
                .message(EErrorType.getMessage())
                .build();
    }


}
package com.ltp.interview.configuration;

import com.ltp.interview.exception.InvalidCredentialsException;
import com.ltp.interview.exception.LoginConflictException;
import com.ltp.interview.exception.UnknownGenderNameException;
import com.ltp.interview.model.dto.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnknownGenderNameException.class)
    public ResponseEntity<ErrorMessage> handleUnknownGenderNameException(final UnknownGenderNameException e) {
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(LoginConflictException.class)
    public ResponseEntity<ErrorMessage> handleLoginConflictException(final LoginConflictException e) {
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleInvalidCredentialsException(final InvalidCredentialsException e) {
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    }

}

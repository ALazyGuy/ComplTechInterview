package com.ltp.interview.configuration;

import com.ltp.interview.exception.InvalidCredentialsException;
import com.ltp.interview.exception.LoginConflictException;
import com.ltp.interview.exception.UnknownGenderNameException;
import com.ltp.interview.model.dto.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnknownGenderNameException.class)
    public ResponseEntity<ErrorMessage> handleUnknownGenderNameException(final UnknownGenderNameException e) {
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getError()));
    }

    @ExceptionHandler(LoginConflictException.class)
    public ResponseEntity<ErrorMessage> handleLoginConflictException(final LoginConflictException e) {
        return ResponseEntity.badRequest().body(new ErrorMessage(e.getError()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleInvalidCredentialsException(final InvalidCredentialsException e) {
        return ResponseEntity.status(401).body(new ErrorMessage(e.getError()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final String errors = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body(new ErrorMessage(errors));
    }

}

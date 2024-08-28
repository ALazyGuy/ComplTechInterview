package com.ltp.interview.controller;

import com.ltp.interview.model.dto.UserLoginRequestDto;
import com.ltp.interview.model.dto.UserRegisterRequestDto;
import com.ltp.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@Validated @RequestBody UserLoginRequestDto userLoginRequestDto) {
        userService.loginUser(userLoginRequestDto);
        return ResponseEntity.ok().build();
        //TODO Add jwt response
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@Validated @RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        userService.registerUser(userRegisterRequestDto);
        return ResponseEntity.ok().build();
        //TODO Add jwt response
    }

}

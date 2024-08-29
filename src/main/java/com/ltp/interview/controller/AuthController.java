package com.ltp.interview.controller;

import com.ltp.interview.model.dto.JwtResponse;
import com.ltp.interview.model.dto.UserLoginRequestDto;
import com.ltp.interview.model.dto.UserRegisterRequestDto;
import com.ltp.interview.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid UserLoginRequestDto userLoginRequestDto) {
        final JwtResponse response = userService.loginUser(userLoginRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> register(@RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) {
        final JwtResponse response = userService.registerUser(userRegisterRequestDto);
        return ResponseEntity.ok(response);
    }

}

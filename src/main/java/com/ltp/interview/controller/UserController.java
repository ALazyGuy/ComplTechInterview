package com.ltp.interview.controller;

import com.ltp.interview.model.dto.UserInfoDto;
import com.ltp.interview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserInfoDto> allUsers() {
        return userService.getAllUsersInfo();
    }

}

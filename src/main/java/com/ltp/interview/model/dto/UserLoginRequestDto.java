package com.ltp.interview.model.dto;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String login;
    private String password;
}

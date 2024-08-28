package com.ltp.interview.model.dto;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String login;
    private String password;
    private String fullName;
    private String genderName;
}

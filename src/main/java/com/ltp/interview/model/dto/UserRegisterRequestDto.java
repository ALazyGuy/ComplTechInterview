package com.ltp.interview.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequestDto {
    @Size(min = 2, max = 50, message = "Invalid login size")
    private String login;
    @Size(min = 7, max = 20, message = "Invalid password size")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[@$!%*#?&])(.*\\d){3}[a-zA-Z\\d@$!%*#?&]*$", message = "Invalid password format")
    private String password;
    @Size(min = 2, max = 256)
    private String fullName;
    private String genderName;
}

package com.ltp.interview.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequestDto {

    @Size(min = 7, max = 20, message = "Invalid password size")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[@$!%*#?&])(.*\\d){3}[a-zA-Z\\d@$!%*#?&]*$", message = "Invalid password format")
    private String password = null;
    @Size(min = 2, max = 256)
    private String fullName = null;
    private String genderName = null;
}

package com.ltp.interview.service;

import com.ltp.interview.model.dto.UserInfoDto;
import com.ltp.interview.model.dto.UserLoginRequestDto;
import com.ltp.interview.model.dto.UserRegisterRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void registerUser(final UserRegisterRequestDto userRegisterRequestDto);
    void loginUser(final UserLoginRequestDto userLoginRequestDto);
    List<UserInfoDto> getAllUsersInfo();
}

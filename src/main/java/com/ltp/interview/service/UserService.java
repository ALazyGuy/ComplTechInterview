package com.ltp.interview.service;

import com.ltp.interview.model.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    JwtResponse registerUser(final UserRegisterRequestDto userRegisterRequestDto);
    JwtResponse loginUser(final UserLoginRequestDto userLoginRequestDto);
    List<UserInfoDto> getAllUsersInfo();
    void deleteUserById(final Long id);
    void deleteUsers(final List<Long> ids);
    Optional<UserInfoDto> updateCurrentUser(final UserUpdateRequestDto userUpdateRequestDto);
    LoadAllUsersWebsocketDto getWebsocketResponseOnLoadUsers();
}

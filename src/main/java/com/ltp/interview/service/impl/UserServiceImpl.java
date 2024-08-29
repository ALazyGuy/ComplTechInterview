package com.ltp.interview.service.impl;

import com.ltp.interview.exception.InvalidCredentialsException;
import com.ltp.interview.exception.LoginConflictException;
import com.ltp.interview.exception.UnknownGenderNameException;
import com.ltp.interview.mapper.UserMapper;
import com.ltp.interview.model.dto.*;
import com.ltp.interview.model.entity.GenderEntity;
import com.ltp.interview.model.entity.UserEntity;
import com.ltp.interview.repository.UserRepository;
import com.ltp.interview.service.GenderService;
import com.ltp.interview.service.UserService;
import com.ltp.interview.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final GenderService genderService;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse registerUser(final UserRegisterRequestDto userRegisterRequestDto) {
        final Optional<GenderEntity> genderEntity = genderService.getGenderByName(userRegisterRequestDto.getGenderName());

        if(genderEntity.isEmpty()) {
            throw new UnknownGenderNameException(userRegisterRequestDto.getGenderName());
        }

        if(userRepository.existsByLogin(userRegisterRequestDto.getLogin())) {
            throw new LoginConflictException();
        }

        final UserEntity userEntity = new UserEntity();
        userEntity.setGenderEntity(genderEntity.get());
        userEntity.setLogin(userRegisterRequestDto.getLogin());
        userEntity.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
        userEntity.setFullName(userRegisterRequestDto.getFullName());
        userRepository.save(userEntity);

        return generateJwtResponse(userRegisterRequestDto.getLogin());
    }

    @Override
    public JwtResponse loginUser(final UserLoginRequestDto userLoginRequestDto) {
        final Optional<UserEntity> userEntity = userRepository.findByLogin(userLoginRequestDto.getLogin());

        if(userEntity.isEmpty()) {
            throw new InvalidCredentialsException();
        }

        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), userEntity.get().getPassword())) {
            throw new InvalidCredentialsException();
        }

        return generateJwtResponse(userLoginRequestDto.getLogin());
    }

    @Override
    public List<UserInfoDto> getAllUsersInfo() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserInfoDto)
                .toList();
    }

    private JwtResponse generateJwtResponse(final String login) {
        final String token = jwtUtils.generateToken(login);
        return new JwtResponse(token);
    }

    @Override
    public void deleteUserById(final Long id) {
        if(id < 0) {
            return;
        }

        userRepository.deleteById(id);
    }

    @Override
    public void deleteUsers(final List<Long> ids) {
        ids.stream()
                .filter(id -> id >= 0)
                .forEach(userRepository::deleteById);
    }

    @Override
    public Optional<UserInfoDto> updateCurrentUser(final UserUpdateRequestDto userUpdateRequestDto) {
        final String login = SecurityContextHolder.getContext().getAuthentication().getName();
        final Optional<UserEntity> userEntity = userRepository.findByLogin(login);
        if(userEntity.isPresent()) {
            final UserEntity user = userEntity.get();
            if(userUpdateRequestDto.getFullName() != null) {
                user.setFullName(userUpdateRequestDto.getFullName());
            }
            if(userUpdateRequestDto.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userUpdateRequestDto.getPassword()));
            }
            final Optional<GenderEntity> genderEntity = genderService.getGenderByName(userUpdateRequestDto.getGenderName());
            genderEntity.ifPresent(user::setGenderEntity);
            userRepository.save(user);
            return Optional.of(userMapper.toUserInfoDto(user));
        }
        return Optional.empty();
    }

    @Override
    public LoadAllUsersWebsocketDto getWebsocketResponseOnLoadUsers() {
        final String login = SecurityContextHolder.getContext().getAuthentication().getName();
        final Optional<UserEntity> userEntity = userRepository.findByLogin(login);
        if(userEntity.isPresent()) {
            final UserInfoDto userInfoDto = userMapper.toUserInfoDto(userEntity.get());
            return new LoadAllUsersWebsocketDto(userInfoDto, "use request GET /users");
        }

        throw new UsernameNotFoundException(login);
    }
}

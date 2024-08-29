package com.ltp.interview.service.impl;

import com.ltp.interview.model.dto.UserDetailsImpl;
import com.ltp.interview.model.entity.UserEntity;
import com.ltp.interview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserEntity> userEntity = userRepository.findByLogin(username);
        return userEntity.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

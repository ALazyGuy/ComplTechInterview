package com.ltp.interview.service;

import com.ltp.interview.model.entity.GenderEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GenderService {
    Optional<GenderEntity> getGenderByName(final String name);
}

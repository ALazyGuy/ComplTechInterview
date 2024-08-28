package com.ltp.interview.service.impl;

import com.ltp.interview.model.entity.GenderEntity;
import com.ltp.interview.repository.GenderRepository;
import com.ltp.interview.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    public Optional<GenderEntity> getGenderByName(final String name) {
        return genderRepository.findByName(name);
    }
}

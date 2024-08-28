package com.ltp.interview.repository;

import com.ltp.interview.model.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    Optional<GenderEntity> findByName(final String name);
}

package com.ltp.interview.repository;

import com.ltp.interview.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByLogin(final String login);
    Optional<UserEntity> findByLogin(final String login);
}

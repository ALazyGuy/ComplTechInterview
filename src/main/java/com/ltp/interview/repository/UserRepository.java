package com.ltp.interview.repository;

import com.ltp.interview.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByLogin(final String login);
    Optional<UserEntity> findByLogin(final String login);
    @Query(nativeQuery = true, value = "call removeByIds(:id1, :id2)")
    @Modifying
    @Transactional
    void removeByIdsInRange(@Param("id1") final Long id1, @Param("id2") final Long id2);
}

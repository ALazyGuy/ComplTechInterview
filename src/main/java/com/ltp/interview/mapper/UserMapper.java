package com.ltp.interview.mapper;

import com.ltp.interview.model.dto.UserInfoDto;
import com.ltp.interview.model.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public UserInfoDto toUserInfoDto(final UserEntity userEntity) {
        return new UserInfoDto(userEntity.getId(), userEntity.getLogin(), userEntity.getFullName(), userEntity.getGenderEntity().getName());
    }

}

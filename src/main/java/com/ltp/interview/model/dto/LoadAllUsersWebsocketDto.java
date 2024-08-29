package com.ltp.interview.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoadAllUsersWebsocketDto {
    private UserInfoDto user;
    private String action;
}

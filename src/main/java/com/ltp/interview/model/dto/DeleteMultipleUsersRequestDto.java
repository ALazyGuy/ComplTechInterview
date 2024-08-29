package com.ltp.interview.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteMultipleUsersRequestDto {
    private List<Long> userIds;
}

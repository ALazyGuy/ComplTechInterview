package com.ltp.interview.model.dto;

import lombok.Data;

@Data
public class DeleteInRangeRequestDto {
    private Long start;
    private Long end;
}

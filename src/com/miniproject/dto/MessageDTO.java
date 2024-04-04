package com.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class MessageDTO {
    private String message;
    private Date sendTime;
}

package com.edu.ecom.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private final boolean success;
    private final String message;


    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}

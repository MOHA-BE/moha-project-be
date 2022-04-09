package com.sparta.moha.dto;

import lombok.Getter;

@Getter
public class ErrorMessageDto {
    private String ok;
    private String message;

    public ErrorMessageDto(String ok, String message) {
        this.ok = ok;
        this.message = message;
    }
}

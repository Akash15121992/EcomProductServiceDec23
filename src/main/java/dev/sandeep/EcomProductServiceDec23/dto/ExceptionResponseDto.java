package dev.sandeep.EcomProductServiceDec23.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponseDto {
    private String message;
    private int code; //error code like 404 , http code

    public ExceptionResponseDto(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
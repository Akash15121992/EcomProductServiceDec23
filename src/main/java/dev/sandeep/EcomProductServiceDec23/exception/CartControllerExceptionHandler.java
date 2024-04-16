package dev.sandeep.EcomProductServiceDec23.exception;

import dev.sandeep.EcomProductServiceDec23.controller.CartController;
import dev.sandeep.EcomProductServiceDec23.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = CartController.class)
public class CartControllerExceptionHandler {

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity handleCartNotFoundException(CartNotFoundException ce){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                ce.getMessage(),
                404
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }
}


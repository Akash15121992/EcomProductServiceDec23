package dev.sandeep.EcomProductServiceDec23.exception;

import dev.sandeep.EcomProductServiceDec23.controller.ProductController;
import dev.sandeep.EcomProductServiceDec23.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class ProductControllerExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class, NoProductPresentException.class})
    public ResponseEntity handleProductNotFoundExceptionAndNoProductPresentException(ProductNotFoundException pe){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                pe.getMessage(),
                404
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity handleInvalidInputException(InvalidInputException ie){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                ie.getMessage(),
                400
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }
   /*
    @ExceptionHandler(CartNotFoundException.class)
     public ResponseEntity handleCartNotFoundException(CartNotFoundException ce){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                ce.getMessage(),
                404
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }
    */

}

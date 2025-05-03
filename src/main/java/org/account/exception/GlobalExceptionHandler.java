package org.account.exception;

import org.account.dto.CustomerDto;
import org.account.entity.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistException(CustomerAlreadyExistException exp, WebRequest webRequest){
        ErrorResponseDto error=new ErrorResponseDto(webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exp.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFOundException exp,WebRequest request){
        ErrorResponseDto erro= new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                exp.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }


}

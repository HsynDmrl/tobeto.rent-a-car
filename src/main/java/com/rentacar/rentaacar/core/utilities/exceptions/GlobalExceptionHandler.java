package com.rentacar.rentaacar.core.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationError(MethodArgumentNotValidException exception)
    {
        return "Validasyon hatası";
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeException(RuntimeException exception)
    {
        return exception.getMessage();
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleError(Exception exception)
    {
        return "Bilinmedik hata";
    }
}

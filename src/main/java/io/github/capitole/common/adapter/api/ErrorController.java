package io.github.capitole.common.adapter.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.capitole.common.domain.ErrorDto;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDto> catchException(IllegalArgumentException exception) {
        var error = ErrorDto.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

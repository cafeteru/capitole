package io.github.cafeteru.capitole.common.util.infrastructure.adapter.in.api;

import io.github.cafeteru.capitole.common.util.infrastructure.adapter.in.api.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> catchIllegalArgumentException(
            final IllegalArgumentException exception) {
        final ErrorDto errorDto = new ErrorDto(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}

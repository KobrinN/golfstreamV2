package ru.golfstream.project.exception.exceptions.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.golfstream.project.exception.ErrorResponse;
import ru.golfstream.project.exception.exceptions.common.BaseException;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e){
        log.error(e.getMessage());
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus().value(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }
}

package ru.golfstream.project.exception.exceptions.common;

import org.springframework.http.HttpStatus;
import ru.golfstream.project.exception.exceptions.common.BaseException;

public class EmptyFieldsException extends BaseException {
    public EmptyFieldsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

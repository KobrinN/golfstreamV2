package ru.golfstream.project.exception.exceptions.common;

import org.springframework.http.HttpStatus;
import ru.golfstream.project.exception.exceptions.common.BaseException;

public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

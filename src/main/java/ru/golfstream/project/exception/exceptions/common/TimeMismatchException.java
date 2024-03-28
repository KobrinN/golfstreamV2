package ru.golfstream.project.exception.exceptions.common;

import org.springframework.http.HttpStatus;
import ru.golfstream.project.exception.exceptions.common.BaseException;

public class TimeMismatchException extends BaseException {
    public TimeMismatchException(String message){
        super(HttpStatus.valueOf(422), message);
    }
}

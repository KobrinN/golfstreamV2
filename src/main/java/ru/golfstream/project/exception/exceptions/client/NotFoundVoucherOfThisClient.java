package ru.golfstream.project.exception.exceptions.client;

import org.springframework.http.HttpStatus;
import ru.golfstream.project.exception.exceptions.common.BaseException;

public class NotFoundVoucherOfThisClient extends BaseException {
    public NotFoundVoucherOfThisClient(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

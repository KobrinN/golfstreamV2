package ru.golfstream.project.exception.exceptions.voucher;

import org.springframework.http.HttpStatus;
import ru.golfstream.project.exception.exceptions.common.BaseException;

public class FillVoucherException extends BaseException {
    public FillVoucherException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}

package xyz.sangdam.counseling.exceptions;

import xyz.sangdam.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class ReservationNotFoundException extends CommonException {
    public ReservationNotFoundException() {
        super("NotFound.reservation", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}

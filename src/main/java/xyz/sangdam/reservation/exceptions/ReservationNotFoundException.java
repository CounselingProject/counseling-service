package xyz.sangdam.reservation.exceptions;

import org.springframework.http.HttpStatus;
import xyz.sangdam.global.exceptions.CommonException;

public class ReservationNotFoundException extends CommonException {
    public ReservationNotFoundException() {
        super("NotFound.reservation", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
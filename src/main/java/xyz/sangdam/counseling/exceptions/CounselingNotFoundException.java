package xyz.sangdam.counseling.exceptions;

import org.springframework.http.HttpStatus;
import xyz.sangdam.global.exceptions.CommonException;

public class CounselingNotFoundException extends CommonException {
    public CounselingNotFoundException() {
        super("NotFound.counseling", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}

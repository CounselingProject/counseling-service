
package xyz.sangdam.counseling.services;

import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.RequestReservation;

@Service
public class ReservationApplyService {

    public void apply(RequestReservation form) {
        Long cNo = form.getCNo();
    }
}

package xyz.sangdam.reservation.controllers;

import xyz.sangdam.reservation.constants.ReservationStatus;
import lombok.Data;

@Data
public class RequestReservationStatus {
    private Long orderNo;
    private ReservationStatus status;
}

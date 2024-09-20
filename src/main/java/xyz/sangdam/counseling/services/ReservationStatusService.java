package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.constants.Status;
import xyz.sangdam.counseling.entities.Reservation;
import xyz.sangdam.counseling.repositories.ReservationRepository;

@Service
@RequiredArgsConstructor
public class ReservationStatusService {
    private final ReservationRepository repository;
    private final ReservationInfoService infoService;

    public void change(Long rNo, Status status) {
        Reservation reservation = infoService.get(rNo);
        reservation.setStatus(status);

        repository.saveAndFlush(reservation);
    }
}
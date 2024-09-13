package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.ReservationSearch;
import xyz.sangdam.counseling.entities.Reservation;
import xyz.sangdam.counseling.repositories.ReservationRepository;
import xyz.sangdam.global.ListData;

@Service
@RequiredArgsConstructor
public class ReservationInfoService {
    private final ReservationRepository reservationRepository;


    public Reservation get(Long rNo) {
        return null;
    }

    public ListData<Reservation> getList(ReservationSearch search) {

        return null;
    }

    private void addInfo(Reservation item) {

    }
}
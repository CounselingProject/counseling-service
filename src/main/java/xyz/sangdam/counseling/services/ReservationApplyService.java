package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.RequestReservation;
import xyz.sangdam.counseling.repositories.GroupReservationRepository;
import xyz.sangdam.counseling.repositories.ReservationRepository;
import xyz.sangdam.member.MemberUtil;

@Service
@RequiredArgsConstructor
public class ReservationApplyService {

    private final ReservationRepository reservationRepository; // 개인 상담
    private final GroupReservationRepository groupReservationRepository; // 집단 상담
    private final MemberUtil memberUtil;

    public void apply(RequestReservation form) {

    }
}

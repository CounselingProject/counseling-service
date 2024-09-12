package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.repositories.GroupReservationRepository;
import xyz.sangdam.counseling.repositories.ReservationRepository;
import xyz.sangdam.member.MemberUtil;

@Service
@RequiredArgsConstructor
public class ReservationApplyService {

    private final CounselingInfoService counselingInfoService;
    private final ReservationRepository reservationRepository;
    private final GroupReservationRepository groupReservationRepository;
    private final MemberUtil memberUtil;

    public void apply() {

    }
}

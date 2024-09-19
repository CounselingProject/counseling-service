package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.member.MemberUtil;

@Service
@RequiredArgsConstructor
public class ReservationUpdateService {
    private final ReservationInfoService infoService;
    private final ReservationStatusService statusService;
    private final MemberUtil memberUtil;

    public void update(Long rNo) {

    }
}
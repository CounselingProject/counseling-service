package xyz.sangdam.reservation.services;

import xyz.sangdam.global.exceptions.UnAuthorizedException;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Member;
import xyz.sangdam.reservation.constants.ReservationStatus;
import xyz.sangdam.reservation.entities.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static xyz.sangdam.reservation.constants.ReservationStatus.*;

@Service
@RequiredArgsConstructor
public class ReservationCancelService {
    private final ReservationInfoService infoService;
    private final ReservationStatusService statusService;
    private final MemberUtil memberUtil;

    public Reservation cancel(Long orderNo) {
        Reservation item = infoService.get(orderNo);
        Member member = memberUtil.getMember();
        Member rMember = item.getMember();
        if (!member.getEmail().equals(rMember.getEmail())) {
            throw new UnAuthorizedException();
        }

        ReservationStatus status = item.getStatus();

        if (status == APPLY || status == START) {
            statusService.change(orderNo, CANCEL);
            item.setStatus(CANCEL);
            item.setStatusStr(CANCEL.getTitle());
        } else if (status == CONFIRM) {
            statusService.change(orderNo, REFUND);
            item.setStatus(REFUND);
            item.setStatusStr(REFUND.getTitle());
        }

        return item;
    }
}
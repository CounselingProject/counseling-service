package xyz.sangdam.counseling.services;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.ReservationSearch;
import xyz.sangdam.counseling.entities.QReservation;
import xyz.sangdam.counseling.entities.Reservation;
import xyz.sangdam.counseling.repositories.ReservationRepository;
import xyz.sangdam.global.ListData;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Member;

@Service
@RequiredArgsConstructor
public class ReservationInfoService {
    private final ReservationRepository reservationRepository;
    private final MemberUtil memberUtil;

    public Reservation get(Long rNo) {
        return null;
    }

    public ListData<Reservation> getList(ReservationSearch search) {
        BooleanBuilder andBuilder = new BooleanBuilder();
        QReservation reservation = QReservation.reservation;
        Member member = memberUtil.getMember();
        if (memberUtil.isCounselor() || memberUtil.isProfessor()) { // 상담사나 교수일 때 상담사 이메일로 본인 것만 조회
            andBuilder.and(reservation.counselorEmail.eq(member.getEmail()));
        } else if (memberUtil.isStudent()) { // 학생일 때 학생 이메일로 본인 것만 조회
            andBuilder.and(reservation.email.eq(member.getEmail()));
        }

        return null;
    }

    private void addInfo(Reservation item) {

    }
}
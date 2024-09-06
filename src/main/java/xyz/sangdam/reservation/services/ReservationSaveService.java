package xyz.sangdam.reservation.services;

import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.reservation.constants.ReservationStatus;
import xyz.sangdam.reservation.controllers.RequestReservation;
import xyz.sangdam.reservation.entities.Reservation;
import xyz.sangdam.reservation.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationSaveService {

    private final ReservationRepository repository;
    private final MemberUtil memberUtil;

    public Reservation save(RequestReservation form) { // 예약 접수

        long orderNo = System.currentTimeMillis();
        String payMethod = form.getPayMethod();
        String mobile = form.getMobile();
        if (StringUtils.hasText(mobile)) {
            mobile = mobile.replaceAll("\\D", "");
        }

        // 상담 추가

        Reservation item = Reservation.builder()
                .orderNo(orderNo)
                .email(form.getEmail())
                .name(form.getName())
                .mobile(mobile)
                .persons(Math.max(form.getPersons(), 1))
                .price(10000)
                // 상담 추가
                .member(memberUtil.getMember())
                .rDateTime(LocalDateTime.of(form.getRDate(), form.getRTime()))
                .status(ReservationStatus.START)
                .build();

        return save(item);

    }

    public Reservation save(Reservation item) {

        repository.saveAndFlush(item);

        return item;
    }
}
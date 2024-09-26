package xyz.sangdam.counseling.validators;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import xyz.sangdam.counseling.controllers.RequestReservation;
import xyz.sangdam.counseling.entities.QReservation;
import xyz.sangdam.counseling.entities.Reservation;
import xyz.sangdam.counseling.repositories.ReservationRepository;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Member;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationValidator implements Validator {

    private final ReservationRepository repository;
    private final MemberUtil memberUtil;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Reservation.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RequestReservation form = (RequestReservation) target;

        Member member = memberUtil.getMember();

        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        String errorCode = "NotAvailable.reservation";
        // 예약일 검증
        if (form.getRdate().isBefore(today)) {
            errors.rejectValue("rdate", errorCode);
        }

        // 예약시간 검증
        if (form.getRdate().isEqual(today) && form.getRtime().isBefore(currentTime)) {
            errors.rejectValue("rtime",errorCode);
        }

        // 중복 예약 검증
        QReservation reservation = QReservation.reservation;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(reservation.rDateTime.eq(form.getRdate().atTime(form.getRtime())))
                .and(reservation.email.eq(member.getEmail()));


        if (repository.exists(builder)) {
            // 중복인 경우 오류 처리
            errors.rejectValue("rtime", errorCode);
        }
    }
}

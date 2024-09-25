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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationValidator implements Validator {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Reservation.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        RequestReservation form = (RequestReservation) target;

        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        String errorCode = "NotAvailable.reservation";
        // 예약일 검증
        if (form.getRDate().isBefore(today)) {
            errors.rejectValue("rDate", errorCode);
        }

        // 예약시간 검증
        if (form.getRDate().isEqual(today) && form.getRTime().isBefore(currentTime)) {
            errors.rejectValue("rTime",errorCode);
        }

        // 중복 예약 검증
        QReservation reservation = QReservation.reservation;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(reservation.rDateTime.eq(form.getRDate().atTime(form.getRTime())));

        List<Reservation> dateVerification = queryFactory.selectFrom(reservation)
                .where(builder)
                .fetch();

        if (!dateVerification.isEmpty()) {
            // 중복인 경우 오류 처리
            errors.rejectValue("rTime", errorCode);
        }
    }
}

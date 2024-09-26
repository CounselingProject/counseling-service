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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CounselingValidator implements Validator {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestReservation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestReservation form = (RequestReservation) target;

        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        String errorCode = "불가능한 예약 입니다.";

        // 예약일 검증 : 과거 날짜에 예약 불가
        if (form.getRdate().isBefore(today)) {
            errors.rejectValue("rDate", errorCode, "신청 당일 기준으로 이전 날짜 신청은 불가능 합니다.");
        }

        // 당일 예약인 경우, 예약 시간이 신청 시간 이후만 가능
        if (form.getRdate().isEqual(today) && form.getRtime().isBefore(currentTime)) {
            errors.rejectValue("rTime", errorCode, "예약 시간의 경우 예약 신청 시간 이전은 불가능 합니다.");
        }

        // 중복 예약 검증
        LocalDateTime reservationDateTime = LocalDateTime.of(form.getRdate(), form.getRtime());
        QReservation reservation = QReservation.reservation;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(reservation.rDateTime.eq(reservationDateTime));

        List<Reservation> existingReservations = queryFactory.selectFrom(reservation)
                .where(builder)
                .fetch();

        if (!existingReservations.isEmpty()) {
            errors.rejectValue("rTime", errorCode, "해당 타임에 예약이 마감되었습니다.");
        }
    }
}

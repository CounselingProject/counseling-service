package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.constants.PersonalCategory;
import xyz.sangdam.counseling.constants.Status;
import xyz.sangdam.counseling.controllers.RequestReservation;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.entities.Reservation;
import xyz.sangdam.counseling.repositories.ReservationRepository;
import xyz.sangdam.global.exceptions.BadRequestException;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationApplyService {

    private final ReservationRepository reservationRepository;
    private final CounselingInfoService counselingInfoService;
    private final MemberUtil memberUtil;

    public void apply(RequestReservation form) {
        // 로그인 여부 및 상담 예약 날짜 확인
        if (!memberUtil.isLogin() || form.getRdate() == null || form.getRdate().isBefore(LocalDate.now())) {
            throw new BadRequestException("접근이 올바르지 않습니다.");
        }

        // 상담 프로그램 조회
        Long cNo = form.getCno(); // 집단 상담 프로그램 번호 (개인 상담일 경우 null일 수 있음)
        Counseling counseling = null;
        if (cNo != null) {
            counseling = counselingInfoService.get(cNo); // 집단 상담 프로그램 조회
        }

        // 예약 날짜와 시간 설정
        LocalDateTime reservationDateTime = LocalDateTime.of(form.getRdate(), form.getRtime());

        // 회원 정보
        Member member = memberUtil.getMember();

        // 예약 정보
        Reservation reservation = Reservation.builder()
                .email(member.getEmail()) // 회원 이메일
                .userName(member.getUserName()) // 회원명
                .reason(form.getReason()) // 상담 사유 (개인 상담 신청 시 작성한 값)
                .rDateTime(reservationDateTime) // 예약 날짜 및 시간
                .status(Status.APPLY) // 예약 상태는 예약 접수 상태로 설정
                .build();

        // 집단 상담인 경우
        if (counseling != null) {
            reservation.setCounseling(counseling); // 집단 상담 프로그램 정보
            reservation.setCounselingName(counseling.getCounselingName()); // 상담 프로그램명
            reservation.setCounselorName(counseling.getCounselorName()); // 상담사명
            reservation.setCounselorEmail(counseling.getCounselorEmail()); // 상담사 이메일
            reservation.setCounselingType(CounselingType.GROUP);
        } else { // 개인 상담인 경우 구분
            PersonalCategory category = form.getCategory() != null ? PersonalCategory.valueOf(form.getCategory()) : null;

            reservation.setCategory(category); // 개인 상담 분류
            reservation.setCounselingType(CounselingType.PERSONAL);
            String counselingName;
            switch (category) {
                case PROFESSOR:
                    counselingName = "교수 상담";
                    break;
                case EMPLOYMENT:
                    counselingName = "취업 상담";
                    break;
                case PSYCHOLOGICAL:
                    counselingName = "심리 상담";
                    break;
                default:
                    counselingName = "미정";
            }

            reservation.setCounselingName(counselingName); // 개인 상담별 구분
        }

        reservationRepository.saveAndFlush(reservation);
    }
}
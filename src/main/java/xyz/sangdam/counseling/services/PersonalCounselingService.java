package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalCounselingService {

    //private final Category counselingType; // 상담 타입별 구분

    /**
     * 개인 상담 신청
     * 1. 교수, 취업, 심리 상담 : counselingType -> PROFESSOR 교수 상담, EMPLOYMENT 취업 상담, PSYCHOLOGICAL 심리 상담 으로 구분
     * 2. 각각의 신청 페이지에서 신청 (<- 프론트)
     * 3. 신청 페이지에서 달력으로 날짜 및 시간 선택하여 신청 (<- 프론트)
     * 4. 상담 신청 과정 (<- 백)
     * - 주중 (월~금)에만 가능, 상담 날짜는 신청일 다음 날부터 가능, 상담 시간은 9:00 부터 18:00 까지 1시간씩 총 9타임으로 구분,
     * - 09:00 ~ 10:00 1타임 / 10:00 ~ 11:00 2타임 / 11:00 ~ 12:00 3타임 / 12:00 ~ 13:00 4타임 / 13:00 ~ 14:00 5타임 / 14:00 ~ 15:00 6타임 / 15:00 ~ 16:00 7타임 / 16:00 ~ 17:00 8타임 / 17:00 ~ 18:00 9타임
     * - 상담별 1일 1회만 가능 (ex. 교수1 취업1 = O | 교수2 = X), 1타임에 1명만 신청 가능
     * - 상담별 상담실 호수 지정하기 (ex. 교수 상담 301호, 취업 상담 302호, 심리 상담 303호)
     * - 신청 시 불러오는 신청 학생 개인 정보 : 학과, 학번, 이름, 연락처, 이메일 -> 로그인한 계정에 저장된 값 알아서 연동
     * 5. 상담 신청 내역은 Reservation 으로 이동할 수 있게끔 연동 (이건 여기서 하거나 예약에서 하거나 결정해야 함)
     * 6. 상담 신청 처리 과정 : 상담 신청 -> 신청 대기 -> 신청 확인 -> 신청 완료 -> 상담 완료 로 구성
     */

}
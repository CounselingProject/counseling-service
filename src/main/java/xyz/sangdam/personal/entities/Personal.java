package xyz.sangdam.personal.entities;

import lombok.Data;

@Data
public class Personal {

    private String CounselingNo; // 개인 상담 번호

    private String ScheduleNo; // 일정 일련 번호

    private String StudentNo; // 학번

    private String EmpNo; // 사번

    private String RegisterDate; // 신청 일자

    private String ReservationDate; // 예약 일자

    private String CounselingType; // 개인 상담 종류

    private String CounselingStyle; // 개인 상담 방식

    private String CounselingState; // 진행 상태
}
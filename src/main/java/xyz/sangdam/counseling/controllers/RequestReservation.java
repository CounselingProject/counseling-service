package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RequestReservation {
    private Long cNo; // 집단상담 프로그램 번호

    /* 개인상담 */
    private String category; // 개인상담 분류

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate rDate; // 예약일

    @JsonFormat(pattern="HH:mm")
    private LocalTime rTime; // 예약시간

    private String reason; // 상담 사유
}
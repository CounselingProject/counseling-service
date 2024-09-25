package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestReservation {

    /* 집단 상담 */
    private Long cNo; // 집단 상담 프로그램 번호

    /* 개인 상담 */
    private String category; // 개인 상담일 경우 상담 분류

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate rDate; // 예약일

    @JsonFormat(pattern="HH:mm")
    private LocalTime rTime; // 예약 시간

    private String reason; // 상담 사유
}
package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestCounseling {

    private Long cNo; // 상담 번호 : 수정 시 필요

    private String mode; // write - 등록, update - 수정

    @NotBlank
    private String gid; // 이미지 등록에 필요

    @NotBlank
    private String counselingDes; // 상담 설명

    @NotBlank
    private String counselorName; // 상담사명

    @NotBlank
    private String counselorEmail; // 상담사 이메일

    @NotBlank
    private String counselingName; // 집단 상담 프로그램명

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationSdate; // 신청 기간 시작 일시

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationEdate; // 신청 기간 종료 일시

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime counselingDate; // 상담일

    private int counselingLimit; // 정원
}

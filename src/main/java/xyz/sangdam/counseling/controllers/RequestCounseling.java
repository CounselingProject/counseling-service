package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class RequestCounseling {

    private Long cNo; // 상담 번호 - 수정 => validator 필요

    private String mode; // write , update

    @NotBlank
    private String gid; // 이미지용 그룹 아이디

   @NotBlank
    private String counselingName; // 프로그램명

    @NotBlank
    private String counselingDes; // 상담 설명

    @NotBlank
    private String counselorName; // 상담사명

    @NotBlank
    private String counselorEmail; // 상담사 이메일

    @JsonFormat(pattern = "yyyy-MM-dd") // 나중에 바꿔서 하는 경우도 있어서 공백허용
    private LocalDate reservationSdate; // 신청 시작일시

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationEdate; // 신청 종료일시


    private int counselingLimit; // 인원수

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime counselingDate; // 상담일


}
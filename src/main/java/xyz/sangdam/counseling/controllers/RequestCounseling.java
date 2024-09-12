package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import xyz.sangdam.counseling.constants.PersonalCategory;

import java.time.LocalDate;



@Data
public class RequestCounseling {
    @NotNull
    private Long counselingNo;

    @NotBlank
    private String counselingType;

    private String counselingName; // 상담명

    @NotBlank
    private String counsellingDes; // 상담 설명

    @NotBlank
    private String counselorName; // 상담사명

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationSdate; // 신청 시작일시

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationEdate; // 신청 종료일시

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate counselingSdate; // 상담 시작일자

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate counselingEdate; // 상담 종료일자

    @Max(15) // 최대 인원 15명만 받도록
    private int counselingLimit; // 인원수

    @NotBlank
    private PersonalCategory category;

    @NotBlank
    private String gid; // 이미지용 그룹 아이디

}
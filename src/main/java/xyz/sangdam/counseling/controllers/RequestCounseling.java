package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RequestCounseling {

    private Long cNo; // 상담 번호 - 수정 시 필요

    private String mode; // write(등록), update(수정)

    @NotBlank
    private String gid; // 이미지 등록에 필요

    @NotBlank
    private String counselingName; // 집단상담 프로그램명

    @NotBlank
    private String counselingDes; // 상담 설명

    @NotBlank
    private String counselorName; // 상담사명

    @NotBlank
    private String counselorEmail; // 상담사 이메일

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate reservationSdate; // 신청기간 시작일자

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate reservationEdate; // 신청기간 종료일자

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime counselingDate; // 상담일시

    private int counselingLimit; // 정원
}

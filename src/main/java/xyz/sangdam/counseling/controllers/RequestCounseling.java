package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Max;
import lombok.Data;

import java.time.LocalDate;



@Data
public class RequestCounseling {

    @Id
    @GeneratedValue
    private Long cNo;

    @Column(length = 45, nullable = false)
    private String gid; // 이미지용 그룹 아이디

    @Column(length=60, nullable = false)
    private String programNm; // 프로그램명

    @Lob
    private String counselingDes; // 상담 설명

    @Column(length=20, nullable = false)
    private String counselorName; // 상담사명

    @Column(length=65, nullable = false)
    private String counselorEmail; // 상담사 이메일

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate reservationSdate; // 신청 시작일시

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate reservationEdate; // 신청 종료일시

    @Max(15)
    private int counselingLimit; // 인원수

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate counselingDate; // 상담일



}
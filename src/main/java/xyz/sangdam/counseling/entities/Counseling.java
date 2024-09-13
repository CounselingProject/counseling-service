package xyz.sangdam.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.constants.PersonalCategory;

import java.time.LocalDate;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Counseling {
    @Id
    @GeneratedValue
    private Long cNo; // 상담 번호

    @Column(length=45, nullable = false)
    private String gid; // 이미지 등록에 필요

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private CounselingType type; // 개인/집단상담 구분

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private PersonalCategory category; // 개인상담 종류

    @Column(length=60, nullable = false)
    private String counselingName; // 상담명

    @Lob
    private String counselingDes; // 상담 설명

    @Column(length=20, nullable = false)
    private String counselorName; // 상담사명

    @Column(length=65, nullable = false)
    private String counselorEmail; // 상담사 이메일

    /* 집단상담 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate reservationSdate; // 신청기간 시작일시

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate reservationEdate; // 신청기간 종료일시

    private LocalDate counselingDate; // 상담일

    private int counselingLimit; // 정원

    @Column(length=60, nullable = false)
    private String programNm; // 프로그램명
}

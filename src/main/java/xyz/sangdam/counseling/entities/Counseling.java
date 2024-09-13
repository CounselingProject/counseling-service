package xyz.sangdam.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.sangdam.global.entities.BaseMemberEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Counseling extends BaseMemberEntity { // 집단상담
    @Id
    @GeneratedValue
    private Long cNo; // 상담 번호

    @Column(length=45, nullable = false)
    private String gid; // 이미지 등록에 필요

    @Column(length=60, nullable = false)
    private String counselingName; // 집단상담 프로그램명

    @Lob
    private String counselingDes; // 상담 설명

    @Column(length=20, nullable = false)
    private String counselorName; // 상담사명

    @Column(length=65, nullable = false)
    private String counselorEmail; // 상담사 이메일

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationSdate; // 신청기간 시작일자

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationEdate; // 신청기간 종료일자

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime counselingDate; // 상담일시

    private int counselingLimit; // 정원
}

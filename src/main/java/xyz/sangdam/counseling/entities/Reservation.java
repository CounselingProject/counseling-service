package xyz.sangdam.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.constants.PersonalCategory;
import xyz.sangdam.counseling.constants.Status;
import xyz.sangdam.global.entities.BaseEntity;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue
    private Long rNo; // 예약 접수 번호

    @Column(length=20)
    @Enumerated(EnumType.STRING)
    private Status status; // 진행상태

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cNo")
    private Counseling counseling;

    @Column(length = 40, nullable = false)
    private String userName; // 로그인 회원명

    @Column(length = 80, nullable = false)
    private String email; // 로그인 회원 이메일

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private CounselingType type; // 개인/집단상담 구분

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private PersonalCategory category; // 개인상담 종류

    @Column(length=60, nullable = false)
    private String counselingName; // 상담명

    @Column(length=20, nullable = false)
    private String counselorName; // 상담사명

    @Column(length=65, nullable = false)
    private String counselorEmail; // 상담사 이메일

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rDateTime; // 예약일시

    @Column(length=60, nullable = false)
    private String programNm; // 프로그램명

    @Lob
    private String reason; // 상담 사유

    @Lob
    private String record; // 상담 일지
}

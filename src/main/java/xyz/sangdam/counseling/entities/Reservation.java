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
    private Status status; // 진행 상태

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private CounselingType counselingType; // 개인, 집단 상담 구분

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cNo")
    private Counseling counseling; // 집단 상담

    @Column(length = 40, nullable = false)
    private String userName; // 로그인 회원명

    @Column(length = 80, nullable = false)
    private String email; // 로그인 회원 이메일

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private PersonalCategory category; // 개인 상담 종류

    @Column(length=60, nullable = false)
    private String counselingName; // 개인 상담명(학생명(학번)님 개인 상담) + 집단 상담 프로그램명

    @Column(length=20, nullable = false)
    private String counselorName; // 상담사명

    @Column(length=65, nullable = false)
    private String counselorEmail; // 상담사 이메일

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rDateTime; // 예약 일시

    @Lob
    private String reason; // 상담 사유

    @Lob
    private String record; // 상담 일지
}
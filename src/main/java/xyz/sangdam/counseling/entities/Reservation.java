package xyz.sangdam.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import xyz.sangdam.counseling.constants.Status;
import xyz.sangdam.global.entities.BaseEntity;

import java.time.LocalDateTime;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Reservation extends BaseEntity {
    @Id @GeneratedValue
    private Long reservationNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="counselingNo")
    private Counseling counseling; // CounselingType으로 개인/집단상담 구분

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status; // 진행상태

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationDate; // 예약일시

    @Column(length = 40, nullable = false)
    private String userName; // 로그인 회원명

    @Column(length = 80, nullable = false)
    private String email; // 로그인 회원 이메일

    @Lob
    private String reason; // 상담 사유

    @Lob
    private String record; // 상담 일지(학생별)
}
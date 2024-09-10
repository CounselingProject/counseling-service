package xyz.sangdam.counseling.entities;

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
    private Counseling counseling; // 개인 상담 또는 그룹 상담

    private Status status; // 진행상태
    private LocalDateTime reservationDate; // 예약일시
    private String userName; // 로그인 회원명
    private String email; // 로그인 회원 이메일

    @Lob
    private String record; // 상담 일지(학생별)

}
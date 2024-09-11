package xyz.sangdam.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import xyz.sangdam.global.entities.BaseMemberEntity;

import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Counseling extends BaseMemberEntity {
    @Id @GeneratedValue
    private Long counselingNo;

    @Column(length = 45, nullable = false)
    private String gid; // 이미지용 그룹 아이디

    @Column(length=60, nullable = false)
    private String counselingName; // 상담명

    @Lob
    private String counsellingDes; // 상담 설명

    @Column(length=20, nullable = false)
    private String counselorName; // 상담사명

    @Column(length=65, nullable = false)
    private String counselorEmail; // 상담사 이메일

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationSdate; // 신청 시작일시

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationEdate; // 신청 종료일시
}
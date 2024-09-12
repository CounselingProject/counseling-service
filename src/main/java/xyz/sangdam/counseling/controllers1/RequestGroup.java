package xyz.sangdam.counseling.controllers1;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestGroup {

    @Id
    @GeneratedValue
    private Long groupNo; // 집단상담 프로그램번호

    @Column(length = 45, nullable = false)
    private String gid; // 이미지용 그룹 아이디

    @Column(length=60, nullable = false)
    private String groupName; // 상담명

    @Lob
    private String groupDes; // 상담 설명

    private String target; // 집단상담프로그램 대상

    @Column(length=20, nullable = false)
    private String counselorName; // 상담사명

    @Column(length=65, nullable = false)
    private String counselorEmail; // 상담사 이메일

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate reservationSdate; // 신청 시작일시

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate reservationEdate; // 신청 종료일시



    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate groupSdate; // 잡단상담 프로그램 시작일자

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate groupEdate; // 집단상담 프로그램 종료일자

    private int groupLimit; // 인원수


}

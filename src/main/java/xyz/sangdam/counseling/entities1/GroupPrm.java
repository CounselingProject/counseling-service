package xyz.sangdam.counseling.entities1;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Builder
@NoArgsConstructor @AllArgsConstructor
@RestController
@Data
public class GroupPrm {

    @Id
    @GeneratedValue
    private Long groupNo; // 집단상담프로그램번호

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
    private LocalDate groupSdate; // 상담 시작일자

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate groupEdate; // 상담 종료일자

    private int groupLimit; // 인원수

}

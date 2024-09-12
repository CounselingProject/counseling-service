package xyz.sangdam.counseling.entities1;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class GroupSchedule {

    @Id
    @GeneratedValue
    private Long groupNo; // 집단상담프로그램번호

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate groupSdate; // 집단상담 프로그램 시작일자

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate groupEdate; // 집단상담 프로그램 종료일자

    //private LocalDate counselingDate; // 상담일

    @Lob
    private String record; // 상담 일지(집단상담별)
}

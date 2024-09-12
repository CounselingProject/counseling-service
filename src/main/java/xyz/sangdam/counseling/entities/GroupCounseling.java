package xyz.sangdam.counseling.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class GroupCounseling extends Counseling {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate counselingSdate; // 상담 시작일자

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate counselingEdate; // 상담 종료일자
    private int counselingLimit;

}

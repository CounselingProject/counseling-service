package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.constants.PersonalCategory;
import xyz.sangdam.global.CommonSearch;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationSearch extends CommonSearch {
    private List<String> email; // 예약건은 본인 것만 조회해야 함

    @DateTimeFormat(pattern="yyyy-MM-dd") // 쿼리스트링으로 값이 넘어옴
    private LocalDate sDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate eDate;

    private CounselingType counselingType; // 개인/집단상담 구분

    private PersonalCategory category; // 개인상담 분류

    private List<String> status; // 진행상태
}

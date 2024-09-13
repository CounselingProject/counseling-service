package xyz.sangdam.counseling.controllers;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.sangdam.global.CommonSearch;

import java.time.LocalDate;

@Data
public class CounselingSearch extends CommonSearch {
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 쿼리스트링 값이므로 형식 알려줘야 함
    private LocalDate sDate; // 시작일

    @DateTimeFormat(pattern = "yyyy-MM-dd") // 쿼리스트링 값이므로 형식 알려줘야 함
    private LocalDate eDate; // 종료일
}
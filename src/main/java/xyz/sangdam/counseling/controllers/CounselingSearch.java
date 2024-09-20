package xyz.sangdam.counseling.controllers;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.sangdam.global.CommonSearch;

import java.time.LocalDate;

@Data
public class CounselingSearch extends CommonSearch {
    private String counselingName; // 프로그램명

    //JSON DATA로 들어오기 때문에 어떤형식인지 알려줘야 한다.
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate sDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate eDate;

    private String sort; // 정렬 조건
}
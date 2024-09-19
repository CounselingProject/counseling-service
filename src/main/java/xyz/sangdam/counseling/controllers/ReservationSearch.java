package xyz.sangdam.counseling.controllers;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.sangdam.global.CommonSearch;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationSearch extends CommonSearch {
    private List<String> email; // 예약건은 본인 것만 조회해야 함

    @DateTimeFormat(pattern="yyyy-MM-dd") // 쿼리스트링으로 값이 넘어옴
    private LocalDate sDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate eDate;
}

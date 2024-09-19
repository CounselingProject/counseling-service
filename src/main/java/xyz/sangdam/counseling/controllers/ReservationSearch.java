package xyz.sangdam.counseling.controllers;

import lombok.Data;
import xyz.sangdam.global.CommonSearch;

import java.util.List;

@Data
public class ReservationSearch extends CommonSearch {
    private List<String> email; // 예약건은 본인 것만 조회해야 함
}
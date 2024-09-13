package xyz.sangdam.counseling.controllers;

import lombok.Data;
import xyz.sangdam.global.CommonSearch;

import java.util.List;

@Data
public class ReservationSearch extends CommonSearch {

    // 일반 소비자 페이지에서는 본인것만 보여야 하기 때문에 추가
    private List<String> email;

}
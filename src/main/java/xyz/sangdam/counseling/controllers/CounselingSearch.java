package xyz.sangdam.counseling.controllers;

import lombok.Data;
import xyz.sangdam.global.CommonSearch;

@Data
public class CounselingSearch extends CommonSearch {
    private String counselingType; // InfoService 의 GetList - 개인, 집단, 전체 프로그램 목록 조회에서 사용
}
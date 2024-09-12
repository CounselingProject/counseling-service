package xyz.sangdam.counseling.controllers;

import lombok.Data;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.global.CommonSearch;

@Data
public class CounselingSearch extends CommonSearch {
    private int page = 1;
    private int limit = 5;

    private String sopt; // 검색 조건
    private String skey; // 검색 키워드


    private CounselingType counselingType;
    private Long counselingNo;

    private String sort; // 정렬 조건


}
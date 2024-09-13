package xyz.sangdam.counseling.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.sangdam.global.rests.JSONData;

@Tag(name="Counseling", description = "상담 API")
@RestController
@RequiredArgsConstructor
public class CounselingController {

    @Operation(summary = "개인/집단 상담 프로그램 목록", description = "type - personal : 개인 상담 목록<br>type - group : 그룹 상담 목록", method="GET")
    @GetMapping("/counseling/{type}")
    public JSONData counselingList(@PathVariable("type") String type) {

        return null;
    }

    @Operation(summary = "개인/집단 상담 프로그램 보기", method="GET")
    @GetMapping("/counseling/info/{counselingNo}")
    public JSONData counselingInfo(@PathVariable("counselingNo") Long cNo) {

        return null;
    }

    @Operation(summary = "개인/집단 상담 신청하기", description = "상담 프로그램 번호(counselingNo)에 따라서 개인 또는 집단 상담을 분리 처리")
    @PostMapping("/counseling/apply")
    public ResponseEntity<Void> apply() {
        /**
         * - 상담 프로그램 번호 : 개인 상담 -> ReservationRepository로 DB 처리
         * - 상담 프로그램 번호 : 집단 상담 -> GroupReservationRepository로 DB 처리
         */

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "개인/집단 신청 목록", description = "type - PERSONAL : 개인 상담 목록<br>GROUP : 집단 상담 목록")
    @GetMapping("/reservation/{type}")
    public JSONData resevationList(@PathVariable("type") String type, @ModelAttribute ReservationSearch search) {

        return null;
    }

    @Operation(summary = "상담 신청 상세 정보", method="GET")
    @GetMapping("/reservation/info/{reservationNo}")
    public JSONData reservationInfo(@PathVariable("reservationNo") Long rNo) {

        return null;
    }

    @Operation(summary = "상담 신청 취소", method="GET")
    @GetMapping("/reservation/cancel/{reservationNo}")
    public void resevationCancel(@PathVariable("reservationNo") Long rNo) {

    }

    @Operation(summary="상담일정/상담이력", description = "상담사/지도교수로 배정된 상담 신청 목록", method="GET")
    @GetMapping("/cs/reservation")
    public JSONData csReservationList(@ModelAttribute ReservationSearch search)  {

        return null;
    }

    @Operation(summary = "상담신청 상세 보기", method="GET")
    @GetMapping("/cs/reservation/{reservationNo}")
    public JSONData csReservationInfo(@PathVariable("reservationNo") Long rNo) {

        return null;
    }

    @Operation(summary = "상담 일정, 상담 상태 변경, 일지 작성", method = "PATCH")
    @PatchMapping("/cs/reservation/{reservationNo}")
    public void csReservationUpdate(@PathVariable("reservationNo") Long rNo) {

    }
}
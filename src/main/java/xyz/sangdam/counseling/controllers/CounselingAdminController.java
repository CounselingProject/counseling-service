package xyz.sangdam.counseling.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.sangdam.global.rests.JSONData;

@Tag(name="CounselingAdmin", description = "상담 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CounselingAdminController {
    private final HttpServletRequest request;

    /**
     * - 개인/집담 상담 프로그램 등록,수정,삭제
     * 		- 담임교수 상담, 취업 상담, 심리 상담
     * 			- CounselingType
     *
     * 		  등록 - POST /admin/counseling
     * 		  수정 - PATCH /admin/counseling/{counselingNo}
     * 		  삭제 - DELETE /admin/counseling/{counselingNo}
     *
     * 	- 개인/집단 상담 프로그램 목록(신청 기간과 상관 없이 노출)
     * 		목록 : GET /admin/counseling
     *
     * 	- 개인/집단 상담 신청 목록
     * 		GET /admin/reservation
     *
     * 	- 개인/집단 상담 신청 상세
     * 		GET /admin/reservation/{reservationNo}
     *
     * - 상담 신청 이력
     * 	- 개인/집단 상담 신청 목록
     * 		GET /admin/reservation
     * 					- Counseling -> CounselingType : 개인상담/집단 상담
     * 	- 개인/집단 상담 신청 상세
     * 		GET /admin/reservation/{reservationNo}
     * 				- Counseling -> CounselingType : 개인상담/집단 상담
     */
    @Operation(summary = "개인/집단 상담 프로그램 등록", description = "counselingType - PERSONAL : 개인 상담 프로그램, GROUP : 집단 상담 프로그램")
    @PostMapping("/counseling")
    public ResponseEntity<Void> registerCounseling() {
        return save();
    }

    @Operation(summary = "개인/집담 상담 프로그램 수정", method = "PATCH")
    @PatchMapping("/counseling/{counselingNo}")
    public ResponseEntity<Void> updateCounseling(@PathVariable("counselingNo") Long cNo) {

        return save();
    }

    public ResponseEntity<Void> save() {

        HttpStatus status = request.getMethod().toUpperCase().equals("POST") ? HttpStatus.CREATED : HttpStatus.OK;

        return ResponseEntity.status(status).build();
    }

    @Operation(summary = "개인/집담 상담 프로그램 삭제", method = "DELETE")
    @DeleteMapping("/counseling/{counselingNo}")
    public void deleteCounseling(@PathVariable("counselingNo") Long cNo) {

        // 상담 프로그램 정보 삭제시 파일 정보도 함께 삭제
    }

    @Operation(summary = "개인/집단 상담 프로그램 목록", method="GET")
    @GetMapping("/counseling")
    public JSONData counselingList(@ModelAttribute CounselingSearch search) {

        return null;
    }

    @Operation(summary = "개인/집단 상담 신청 목록", method="GET")
    @GetMapping("/reservation")
    public JSONData reservationList(@ModelAttribute ReservationSearch search) {

        return null;
    }

    @Operation(summary = "개인/집단 상담 신청 상세")
    @GetMapping("/reservation/{reservationNo}")
    public JSONData reservationInfo(@PathVariable("reservationNo") Long rNo) {

        return null;
    }
}
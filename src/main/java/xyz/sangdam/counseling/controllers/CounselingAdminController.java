package xyz.sangdam.counseling.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.services.CounselingDeleteService;
import xyz.sangdam.counseling.services.CounselingInfoService;
import xyz.sangdam.counseling.services.CounselingSaveService;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Utils;
import xyz.sangdam.global.exceptions.BadRequestException;
import xyz.sangdam.global.rests.JSONData;

@Tag(name="CounselingAdmin", description = "상담 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CounselingAdminController {

    private final HttpServletRequest request;
    private final CounselingSaveService counselingSaveService;
    private final CounselingInfoService counselingInfoService;
    private final CounselingDeleteService counselingDeleteService;
    private final Utils utils;

    @Operation(summary = "집단 상담 등록 수정", description = "POST 방식 요청 - 등록, PATCH 방식 요청 - 수정")
    @RequestMapping(path="/counseling", method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Void> save(@Valid @RequestBody RequestCounseling form, Errors errors) { // 상담 등록 + 수정

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors)); // 에러 확인
        }

        counselingSaveService.save(form);

        HttpStatus status = request.getMethod().toUpperCase().equals("POST") ? HttpStatus.CREATED : HttpStatus.OK; // equals = 방식 통일 | POST 방식

        return ResponseEntity.status(status).build();
    }

    @Operation(summary = "집단 상담 프로그램 조회", method = "GET")
    @GetMapping("/counseling/info/{cNo}")
    public JSONData info(@PathVariable("cNo") Long cNo) { // 1개 조회
        Counseling counseling = counselingInfoService.get(cNo);

        return new JSONData(counseling); // JSONData 값으로 보냄
    }

    @Operation(summary = "집단 상담 프로그램 목록 조회", method = "GET")
    @GetMapping("/counseling")
    public JSONData list(CounselingSearch search) {
        ListData<Counseling> data = counselingInfoService.getList(search);

        return new JSONData(data); // JSONData 값으로 보냄
    }

    @Operation(summary = "집단 상담 프로그램 삭제", method = "DELETE")
    @DeleteMapping("/counseling/{cNo}")
    public void delete(@PathVariable("cNo") Long cNo) {
        counselingDeleteService.delete(cNo);
    }

    @Operation(summary = "상담 예약 신청 목록", method = "GET")
    @GetMapping("/reservation")
    public JSONData reservationList(ReservationSearch search) {

        return null;
    }

    @Operation(summary = "상담 예약 상태 변경")
    @PatchMapping("/reservation")
    public void reservationStatusChange() {

    }
}
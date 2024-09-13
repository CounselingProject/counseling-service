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
import xyz.sangdam.counseling.services.CounselingDeleteService;
import xyz.sangdam.counseling.services.CounselingInfoService;
import xyz.sangdam.counseling.services.CounselingSaveService;
import xyz.sangdam.global.Utils;
import xyz.sangdam.global.exceptions.BadRequestException;
import xyz.sangdam.global.rests.JSONData;

@Tag(name = "CounselingAdmin", description = "상담 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CounselingAdminController {
    private final HttpServletRequest request;
    private final CounselingSaveService counselingSaveService;
    private final Utils utils;
    private final CounselingInfoService counselingInfoService;
    private final CounselingDeleteService counselingDeleteService;


    // 사이트에서 버튼을 누르면 응답을 엔티티 등록
    @Operation(summary = "집단 상담프로그램 등록/수정", description = "POST 방식 요청 - 등록, PATCH 방식 -요청 -수정")
    @RequestMapping(path = "/counseling", method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<Void> save(@Valid @RequestBody RequestCounseling form, Errors errors) {

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        counselingSaveService.save(form);

        HttpStatus status = request.getMethod().toUpperCase().equals("POST") ? HttpStatus.CREATED : HttpStatus.OK;

        return ResponseEntity.status(status).build();
    }


        // 삭제라 반환값이 JSONDATA인지 상관없고 반환값도 필요치 않음
    @Operation(summary = "집단 상담 프로그램 삭제 ", method = "DELETE")
    @DeleteMapping("/counseling/{cNo}")
    public void delete(@PathVariable("cNo") Long cNo) {
        counselingDeleteService.delete(cNo);
    }
    @Operation(summary="상담 예약 신청 목록")
    @GetMapping("/reservation")
    public JSONData reservationList(ReservationSearch search) {

        return null;
    }

    @Operation(summary = "상담 예약 상태 변경")
    @PatchMapping("/reservation")
    public void reservationStatusChange() {

    }
}
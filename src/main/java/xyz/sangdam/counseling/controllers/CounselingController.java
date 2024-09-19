package xyz.sangdam.counseling.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.entities.Reservation;
import xyz.sangdam.counseling.services.CounselingInfoService;
import xyz.sangdam.counseling.services.ReservationApplyService;
import xyz.sangdam.counseling.services.ReservationInfoService;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Utils;
import xyz.sangdam.global.exceptions.BadRequestException;
import xyz.sangdam.global.rests.JSONData;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Member;

import java.util.List;

@Tag(name="Counseling", description = "상담 API")
@RestController
@RequiredArgsConstructor
public class CounselingController {

    private final CounselingInfoService counselingInfoService;
    private final ReservationApplyService reservationApplyService;
    private final ReservationInfoService reservationInfoService;
    private final MemberUtil memberUtil;
    private final Utils utils;

    @Operation(summary = "집단 상담 프로그램 조회", method="GET")
    @GetMapping("/counseling/info/{cNo}")
    public JSONData info(@PathVariable("cNo") Long cNo) {
        Counseling counseling = counselingInfoService.get(cNo);

        return new JSONData(counseling);
    }

    @Operation(summary = "집단 상담 프로그램 목록 조회", method = "GET")
    @GetMapping("/counseling")
    public JSONData list(CounselingSearch search) {
        ListData<Counseling> data = counselingInfoService.getList(search);

        return new JSONData(data);
    }

    @Operation(summary = "예약 신청 접수", method = "POST")
    @PostMapping("/apply")
    public ResponseEntity<Void> apply(@Valid @RequestBody RequestReservation form, Errors errors) {

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        // 예약 신청 처리
        reservationApplyService.apply(form);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "예약 신청 목록", method = "GET")
    @GetMapping("/apply")
    public JSONData applyList(@ModelAttribute ReservationSearch search) {
        Member member = memberUtil.getMember();
        search.setEmail(List.of(member.getEmail()));

        ListData<Reservation> data = reservationInfoService.getList(search);
        return new JSONData(data);
    }

    @Operation(summary="예약 신청 상세 정보", method = "GET")
    @GetMapping("/apply/{rNo}")
    public JSONData applyInfo(@PathVariable("rNo") Long rNo) {
        Reservation item = reservationInfoService.get(rNo);
        return new JSONData(item);
    }

    @Operation(summary = "예약 상태 변경", method="PATCH")
    @PatchMapping("/apply/{rNo}")
    public void applyChange(@PathVariable("rNo") Long rNo) {

    }
}
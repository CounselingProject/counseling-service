package xyz.sangdam.counseling.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.services.CounselingInfoService;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Utils;
import xyz.sangdam.global.exceptions.BadRequestException;
import xyz.sangdam.global.rests.JSONData;

@Tag(name="Counseling", description = "상담 API")
@RestController
@RequiredArgsConstructor
public class CounselingController {

    private final CounselingInfoService counselingInfoService;
    private final Utils utils;

    // 집단상담프로그램 조회 시에는 JsonData값으로 받아야 하니까 JSONdata
    @Operation(summary = "집단상담 프로그램 조회", method = "GET")
    @GetMapping("/counseling/info/{cNo}")
    public JSONData info(@PathVariable("cNo") Long cNo) {
        Counseling counseling = counselingInfoService.get(cNo);

        return new JSONData(counseling);
    }

    @Operation(summary = "집단 상담 프로그램 목록 조회 ", method = "GET")
    @GetMapping("/counseling")
    public JSONData list(CounselingSearch search) {
        ListData<Counseling> data = counselingInfoService.getList(search);

        return new JSONData(data);
    }

    public ResponseEntity<Void> apply (@Valid @RequestBody RequestReservation form , Errors errors) {
        // cNo 들어오면 집단 상담 날짜만 들어왔다 개인상담으로 구분

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));

        }
        // 서비스 추가 예약 남은 서비스 말하시는 듯

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

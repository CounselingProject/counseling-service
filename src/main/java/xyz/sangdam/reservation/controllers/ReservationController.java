package xyz.sangdam.reservation.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Utils;
import xyz.sangdam.global.exceptions.BadRequestException;
import xyz.sangdam.global.rests.JSONData;
import xyz.sangdam.reservation.entities.Reservation;
import xyz.sangdam.reservation.services.ReservationCancelService;
import xyz.sangdam.reservation.services.ReservationInfoService;
import xyz.sangdam.reservation.services.ReservationSaveService;
import xyz.sangdam.reservation.validators.ReservationValidator;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationValidator validator;
    private final ReservationSaveService saveService;
    private final Utils utils;
    private final ReservationInfoService infoService;
    private final ReservationCancelService cancelService;

    @PostMapping("/apply")
    public JSONData apply(@Valid @RequestBody RequestReservation form, Errors errors) {

        validator.validate(form, errors);

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        Reservation reservation = saveService.save(form);

        return new JSONData();
    }

    public void payProcess() {

    }

    /**
     * 예약 취소
     * @param orderNo
     * @return
     */
    @GetMapping("/cancel/{orderNo}")
    @PreAuthorize("isAuthenticated()")
    public JSONData cancel(@PathVariable("orderNo") Long orderNo) {
        Reservation item = cancelService.cancel(orderNo);

        return new JSONData(item);
    }

    /**
     * 목록 조회
     *
     * @param search
     */
    @GetMapping("/list")
    public JSONData list(@ModelAttribute ReservationSearch search) {

        ListData<Reservation> data = infoService.getList(search);
        return new JSONData(data);
    }

    /**
     * 상세 조회 (개별 조회)
     * @param orderNo
     * @return
     */

    @GetMapping("/info/{orderNo}")
    public JSONData info(@PathVariable("orderNo") Long orderNo) {
        Reservation item = infoService.get(orderNo);
        return new JSONData(item);
    }

}
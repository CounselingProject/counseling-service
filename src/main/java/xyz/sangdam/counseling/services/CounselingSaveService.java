package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.RequestCounseling;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.repositories.CounselingRepository;

@Service
@RequiredArgsConstructor
public class CounselingSaveService {

    private final CounselingRepository counselingRepository;

    public Counseling save(RequestCounseling form) {

        Long cNo = form.getCNo();

        Counseling item = Counseling.builder()
                .cNo(cNo)
                .type(form.getType())
                .counselingName(form.getCounselingName())
                .counselingDes(form.getCounselingDes())
                .counselingDate(form.getCounselingDate())
                .counselingLimit(form.getCounselingLimit())
                .counselorName(form.getCounselorName())
                .counselorEmail(form.getCounselorEmail())
                .gid(form.getGid())
                .reservationSdate(form.getReservationSdate())
                .reservationEdate(form.getReservationEdate())
                .programNm(form.getProgramNm())
                .build();

        return save(item);
    }

    public Counseling save(Counseling item) {
        counselingRepository.saveAndFlush(item);
        return item;
    }
}
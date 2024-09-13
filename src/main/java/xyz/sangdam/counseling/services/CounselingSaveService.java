package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.RequestCounseling;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.exceptions.CounselingNotFoundException;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.file.services.FileUploadDoneService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CounselingSaveService {

    private final CounselingRepository repository;
    private final ModelMapper modelMapper;
    private final FileUploadDoneService uploadDoneService;

    public void save(RequestCounseling form) {  // 상담 등록 + 수정
        Long cNo = form.getCNo();
        String mode = Objects.requireNonNullElse(form.getMode(), "write"); // write 가 기본값

        Counseling counseling = null;
        if (mode.equals("update") && cNo != null) {
            counseling = repository.findById(cNo).orElseThrow(CounselingNotFoundException::new);
        } else {
            counseling = new Counseling(); // counseling = Counseling.builder().gid(form.getGid()).build(); 와 동일
            counseling.setGid(form.getGid()); // 처음 등록할 때만 그룹 아이디인 gid 등록함
        }

        counseling.setCounselingDes(form.getCounselingDes()); // 상담 설명
        counseling.setCounselingName(form.getCounselingName()); // 상담 프로그램명
        counseling.setCounselorName(form.getCounselorName()); // 상담사명
        counseling.setCounselorEmail(form.getCounselorEmail()); // 상담사 이메일

        counseling.setReservationSdate(form.getReservationSdate()); // 상담 신청 시작 일자
        counseling.setReservationEdate(form.getReservationEdate()); // 상담 신청 종료 일자

        counseling.setCounselingDate(form.getCounselingDate()); // 상담일
        counseling.setCounselingLimit(form.getCounselingLimit()); // 상담 프로그램 정원

        repository.saveAndFlush(counseling); // counseling 값을 repository 에 저장

        uploadDoneService.process(form.getGid()); // 파일 업로드 완료 처리
    }
}
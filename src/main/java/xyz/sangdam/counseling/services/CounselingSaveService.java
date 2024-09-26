package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.sangdam.counseling.controllers.RequestCounseling;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.exceptions.CounselingNotFoundException;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.file.services.FileUploadDoneService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CounselingSaveService {

    private final CounselingRepository repository;
    private final ModelMapper modelMapper;
    private final FileUploadDoneService uploadDoneService;

    public void save(RequestCounseling form) {

        Long cNo = form.getCNo();
        String mode = Objects.requireNonNullElse(form.getMode(), "write");
        Counseling counseling = null;
        if (mode.equals("update") && cNo != null) {
            counseling = repository.findById(cNo).orElseThrow(CounselingNotFoundException::new);
        } else {
            counseling = new Counseling();
            counseling.setGid(form.getGid()); // 파일 그룹아이디는 만들 때 처음 한번만 생성
        }

        counseling.setCounselingDes(form.getCounselingDes());
        counseling.setCounselingName(form.getCounselingName());
        counseling.setCounselorName(form.getCounselorName());
        counseling.setCounselorEmail(form.getCounselorEmail());

        counseling.setReservationSdate(form.getReservationSdate());
        counseling.setReservationEdate(form.getReservationEdate());

        String counselingDate = form.getCounselingDate();
        if (StringUtils.hasText(counselingDate)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            counseling.setCounselingDate(LocalDateTime.parse(counselingDate, formatter));
        }
        counseling.setCounselingLimit(form.getCounselingLimit());

        repository.saveAndFlush(counseling);

        uploadDoneService.process(form.getGid());
    }
}
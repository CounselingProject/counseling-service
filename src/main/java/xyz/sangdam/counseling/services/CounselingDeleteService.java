package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.repositories.CounselingRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CounselingDeleteService {

    private final CounselingRepository repository;
    private final CounselingInfoService infoService;

    public void delete(Long cNo) {
        Counseling data = infoService.get(cNo);
        data.setDeletedAt(LocalDateTime.now());

        repository.saveAndFlush(data);
        // 값이 안 보이게끔
    }
}
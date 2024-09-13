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

    public void delete(Long cNo) { // 외래키 제약 조건 때문에 완전 삭제가 불가능하므로 deletedAt 을 사용
        Counseling data = infoService.get(cNo);
        data.setDeletedAt(LocalDateTime.now());

        repository.saveAndFlush(data); // data 저장
    }
}
package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.CounselingSearch;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.counseling.repositories.GroupCounselingRepository;
import xyz.sangdam.counseling.repositories.PersonalCounselingRepository;
import xyz.sangdam.global.ListData;

@Service
@RequiredArgsConstructor
public class CounselingInfoService {
    private final PersonalCounselingRepository personalRepository;
    private final GroupCounselingRepository groupRepository;
    private final CounselingRepository counselingRepository; // 목록 조회시

    public Counseling get(Long cNo) {
        return null;
    }

    public ListData<Counseling> getList(CounselingSearch search) {
        return null;
    }

    private void addInfo(Counseling item) {

    }
}
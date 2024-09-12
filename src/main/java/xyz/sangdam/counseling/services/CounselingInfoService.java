package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.controllers.CounselingSearch;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.exceptions.CounselingNotFoundException;
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


    public Counseling get(Long cNo) // 하나 조회 시
    {
        Counseling item = counselingRepository.findById(cNo).orElseThrow(CounselingNotFoundException::new);

        if (CounselingType.PERSONAL == CounselingType.valueOf("personal")) {
            personalRepository.findById(cNo).orElseThrow(CounselingNotFoundException::new);
        }

        if (CounselingType.GROUP == CounselingType.valueOf("group")) {
            groupRepository.findById(cNo).orElseThrow(CounselingNotFoundException::new);
        }


        return item;
    }

    // 목록 조회는 그룹상담만 해당
    public ListData<Counseling> getList(CounselingSearch search) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit(); // 한페이지당 보여줄 레코드 개수
        limit = limit < 1 ? 10 : limit;
        int offset = (page - 1) * limit; // 레코드 시작 위치 구하기

        if (CounselingType.GROUP == CounselingType.valueOf("group")) {
            groupRepository.findById()
        }


        return null;
    }

    // 2차가공
    private void addInfo(Counseling item) {

    }
}
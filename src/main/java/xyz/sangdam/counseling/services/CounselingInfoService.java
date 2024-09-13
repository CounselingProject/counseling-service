package xyz.sangdam.counseling.services;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.controllers.CounselingSearch;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.entities.QCounseling;
import xyz.sangdam.counseling.exceptions.CounselingNotFoundException;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.global.ListData;

@Service
@RequiredArgsConstructor
public class CounselingInfoService {
    private final CounselingRepository counselingRepository;

    public Counseling get(Long cNo)
    // 집단상담프로그램 개별조회
    {
        Counseling item = counselingRepository.findById(cNo).orElseThrow(CounselingNotFoundException::new);

        return item;
    }
    //집단 상담프로그램 목록조회
    public ListData<Counseling> getList(CounselingSearch search) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit(); // 한페이지당 보여줄 레코드 개수
        limit = limit < 1 ? 10 : limit;
        int offset = (page - 1) * limit; // 레코드 시작 위치 구하기

        QCounseling counseling = QCounseling.counseling;
        BooleanBuilder andBuilder = new BooleanBuilder()

        return null;
    }

    private void addInfo(Counseling item) {

    }
}
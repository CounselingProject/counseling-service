package xyz.sangdam.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.sangdam.counseling.controllers.CounselingSearch;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.entities.QCounseling;
import xyz.sangdam.counseling.exceptions.CounselingNotFoundException;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.file.entities.FileInfo;
import xyz.sangdam.file.services.FileInfoService;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Pagination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class CounselingInfoService {
    private final CounselingRepository repository;
    private final FileInfoService fileinfoService;
    private final HttpServletRequest request;

    public Counseling get(Long cNo)
    // 집단상담 프로그램 개별조회
    {

        // 삭제를 하면 조회가 안되게끔 하기 위해서 상담정보에서 메서드 추가!
        BooleanBuilder builder = new BooleanBuilder();
        QCounseling counseling = QCounseling.counseling;
        builder.and(counseling.cNo.eq(cNo))
                .and(counseling.deletedAt.isNull());

        Counseling item = repository.findOne(builder).orElseThrow(CounselingNotFoundException::new);

        return item;
    }
    // 집단상담 프로그램 목록조회 및 검색

    public ListData<Counseling> getList(CounselingSearch search) {

        int page = Math.max(search.getPage(), 1); // 페이지가 0이거나 음수이면 1이 나오도록 설정  , 1 페이지부터 시작하게
        int limit = search.getLimit(); // 한 페이지당 보여줄 레코드 개수
        limit = limit < 1 ? 20 : limit;
        int offset = (page - 1) * limit; // 레코드 시작 위치 구하기

        /* 검색 처리 S */
        BooleanBuilder andBuilder = new BooleanBuilder();
        QCounseling counseling = QCounseling.counseling;

        String sopt = search.getSopt();
        String skey = search.getSkey();

        andBuilder.and(counseling.deletedAt.isNull());

        sopt = StringUtils.hasText(sopt) ? sopt.toUpperCase() : "ALL";
        if (StringUtils.hasText(skey)) {
            skey = skey.trim();
            StringExpression expression = null;
            if (sopt.equals("COUNSELING_NAME")) {
                expression = counseling.counselingName;
            } else if (sopt.equals("COUNSELOR")) {
                expression = counseling.counselorName.concat(counseling.counselorEmail);
            } else {
                // 통합검색
                expression = counseling.counselingName.concat(counseling.counselorEmail).concat(counseling.counselorName);
            }

            if (expression != null) {
                andBuilder.and(expression.contains(skey));
            }
        }

        // 신청일 검색
        LocalDate sDate = search.getSDate(); // 검색 시작일
        LocalDate eDate = search.getEDate(); // 검색 종료일
        if (sDate != null) {    // goe 크거나 같다 00시 (12시)
            andBuilder.and(counseling.counselingDate.goe(sDate.atStartOfDay()));
            if (eDate != null) { // 종료일 < 11시 > 59분까지 넣기 위해서
                andBuilder.and(counseling.counselingDate.loe(eDate.atTime(LocalTime.MAX)));
            }
        }

        /* 검색 처리 E */

        // 페이지형태로 가져오기  , 스프링데이터에서 가져오는 페이지 번호는 0부터 시작하기 때문에 -1 해준 것
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createAt")));

        Page<Counseling> data = repository.findAll(andBuilder, pageable);

        long total = repository.count(andBuilder);
        Pagination pagination = new Pagination(page, (int) total, 10, limit, request);

        List<Counseling> items = data.getContent();
        if (items != null && items.isEmpty()) {
            items.forEach(this::addInfo);
        }

        return new ListData<>(items, pagination);

    }

    private void addInfo(Counseling item) {
        try {
            List<FileInfo> editorImages = fileinfoService.getList(item.getGid(), "editor");
            item.setEditorImages(editorImages);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}


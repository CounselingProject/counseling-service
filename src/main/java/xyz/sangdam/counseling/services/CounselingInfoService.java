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

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class CounselingInfoService {

    private final CounselingRepository repository;
    private final FileInfoService fileInfoService;
    private final HttpServletRequest request;

    public Counseling get(Long cNo) { // 한 개 조회
        Counseling item = repository.findById(cNo).orElseThrow(CounselingNotFoundException::new);

        addInfo(item); // 추가 처리

        return item;
    }

    public ListData<Counseling> getList(CounselingSearch search) { // 목록 조회
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 20 : limit; // 기본값 = 20 (1 페이지당 20개)

        /* 검색 처리 S */
        BooleanBuilder andBuilder = new BooleanBuilder();
        QCounseling counseling = QCounseling.counseling;
        String sopt = search.getSopt();
        String skey = search.getSkey();

        sopt = StringUtils.hasText(sopt) ? sopt.toUpperCase() : "ALL"; // 기본값 = ALL | toUpperCase = 대/소문자 동일하게 처리
        if (StringUtils.hasText(skey)) {
            skey = skey.trim(); // 공백 제거
            StringExpression expression = null;
            if (sopt.equals("COUNSELING_NAME")) {
                expression = counseling.counselingName;
            } else if (sopt.equals("COUNSELOR_NAME")) {
                expression = counseling.counselorName.concat(counseling.counselorEmail); // 상담사명 & 상담사 이메일
            } else { // 통합 검색
                expression = counseling.counselorName.concat(counseling.counselorEmail).concat(counseling.counselingName); // 상담사명 & 상담사 이메일 & 상담 프로그램명
            }

            if (expression != null) {
                andBuilder.and(expression.contains(skey));
            }
        }
        /* 검색 처리 E */

        /* 페이징 처리 */
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt"))); // 스프링의 페이지는 0부터 시작하므로 - 1 해야 1페이지 부터 시작함
        Page<Counseling> data = repository.findAll(andBuilder, pageable);

        long total = repository.count(andBuilder);
        Pagination pagination = new Pagination(page, (int)total, 10, limit, request);

        List<Counseling> items = data.getContent();
        if (items != null && !items.isEmpty()) {
            items.forEach(this::addInfo);
        }

        return new ListData<>(items, pagination);
    }

    private void addInfo(Counseling item) { // 추가 정보
        try { // 이미지 추가 기능
            List<FileInfo> editorImages = fileInfoService.getList(item.getGid(), "editor");
            item.setEditorImages(editorImages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
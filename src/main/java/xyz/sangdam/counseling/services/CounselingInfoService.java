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
import xyz.sangdam.counseling.entities.QReservation;
import xyz.sangdam.counseling.exceptions.CounselingNotFoundException;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.counseling.repositories.ReservationRepository;
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
    private final FileInfoService fileInfoService;
    private final HttpServletRequest request;
    private final ReservationRepository reservationRepository;

    public Counseling get(Long cNo) {
        BooleanBuilder builder = new BooleanBuilder();
        QCounseling counseling = QCounseling.counseling;
        builder.and(counseling.cNo.eq(cNo))
                .and(counseling.deletedAt.isNull()); // deletedAt이 null인 것만 조회(소프트 삭제)

        Counseling item = repository.findOne(builder).orElseThrow(CounselingNotFoundException::new);

        // 추가 처리

        addInfo(item);

        return item;
    }

    public ListData<Counseling> getList(CounselingSearch search) {
        int page = Math.max(search.getPage(), 1);
        int limit = search.getLimit();
        limit = limit < 1 ? 20 : limit;

        /* 검색 처리 S */
        BooleanBuilder andBuilder = new BooleanBuilder();
        QCounseling counseling = QCounseling.counseling;

        // 검색 옵션 및 키워드 처리
        String sopt = search.getSopt();
        String skey = search.getSkey();

        andBuilder.and(counseling.deletedAt.isNull()); // deletedAt이 null인 것만 조회(소프트 삭제)

        sopt = StringUtils.hasText(sopt) ? sopt.toUpperCase() : "ALL";
        if (StringUtils.hasText(skey)) {
            skey = skey.trim();
            StringExpression expression = null;
            if (sopt.equals("COUNSELING_NAME")) {
                expression = counseling.counselingName;
            } else if (sopt.equals("COUNSELOR")) {
                expression = counseling.counselingName.concat(counseling.counselorEmail);
            } else { // 통합 검색
                expression = counseling.counselingName
                        .concat(counseling.counselorEmail)
                        .concat(counseling.counselorName);
            }

            if (expression != null) {
                andBuilder.and(expression.contains(skey));
            }
        }

        // 상담일 검색
        LocalDate sDate = search.getSDate();
        LocalDate eDate = search.getEDate();
        if (sDate != null) {
            andBuilder.and(counseling.counselingDate.goe(sDate.atStartOfDay())); // atStartOfDay(하루의 시작 시간)
        }

        if (eDate != null) {
            andBuilder.and(counseling.counselingDate.loe(eDate.atTime(LocalTime.MAX))); // LocalDate에 시간 추가 - atTime, LocalTime.MAX(하루의 마지막 시간)

        }
        /* 검색 처리 E */

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt"))); // page -1 (pageable 첫번째 페이지 0부터 시작)
        Page<Counseling> data = repository.findAll(andBuilder, pageable);

        long total = repository.count(andBuilder);
        Pagination pagination = new Pagination(page, (int)total, 10, limit, request);

        List<Counseling> items = data.getContent();
        if (items != null && !items.isEmpty()) {
            items.forEach(this::addInfo);
        }

        return new ListData<>(items, pagination);
    }

    private void addInfo(Counseling item) {
        try {
            List<FileInfo> editorImages = fileInfoService.getList(item.getGid(), "editor");
            item.setEditorImages(editorImages);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 집단 상담 신청 인원수
        QReservation reservation = QReservation.reservation;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(reservation.counseling.cNo.eq(item.getCNo()));
        long applicationsCount = reservationRepository.count(builder);
        item.setApplicantsCount(applicationsCount);
    }
}
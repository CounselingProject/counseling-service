package xyz.sangdam.counseling.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.controllers.CounselingSearch;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.entities.GroupCounseling;
import xyz.sangdam.counseling.entities.PersonalCounseling;
import xyz.sangdam.counseling.entities.QCounseling;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.counseling.repositories.GroupCounselingRepository;
import xyz.sangdam.counseling.repositories.PersonalCounselingRepository;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Pagination;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CounselingInfoService {
    private static final Logger logger = LoggerFactory.getLogger(CounselingInfoService.class);


    private final PersonalCounselingRepository personalRepository;
    private final GroupCounselingRepository groupRepository;
    private final CounselingRepository counselingRepository; // 목록 조회시

    public Counseling get(Long cNo) {
        return counselingRepository.findById(cNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 상담 프로그램을 찾을 수 없습니다. ID : " + cNo));
    }

    /**
     * 상담 목록 조회
     * @param search
     * @return
     */
    public ListData<Counseling> getList(CounselingSearch search) {
        int page = Math.max(search.getPage(), 1); // 페이지가 0이거나 음수 이면 1로 설정
        int limit = search.getLimit(); // 한 페이지당 보여줄 레코드 개수
        limit = limit < 1 ? 10 : limit;

        // 예약 신청 날짜로 내림차순 정렬
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "reservationSdate"));

        /* 검색 처리 S */
        QCounseling counseling = QCounseling.counseling;
        BooleanBuilder andBuilder = new BooleanBuilder();

        // 검색 옵션 및 키워드 처리
        String sopt = search.getSopt();
        String skey = search.getSkey();

        sopt = StringUtils.hasText(sopt) ? sopt.trim() : "ALL"; // 기본값 = 통합 검색
        skey = StringUtils.hasText(skey) ? skey.trim() : null;

        if (skey != null) {
            BooleanExpression condition = null;
            if (sopt.equals("ALL")) { // 개인 상담 및 그룹 상담 프로그램명, 상담자명 검색
                condition = counseling.counselingName.contains(skey).or(counseling.counselorName.contains(skey));
            } else if (sopt.equals("NAME")) { // 상담 프로그램명 검색
                condition = counseling.counselingName.contains(skey);
            } else if (sopt.equals("COUNSELOR")) { // 상담자명 검색
                condition = counseling.counselorName.contains(skey);
            } else if (sopt.equals("PERSONAL")) { // 개인 상담 프로그램
                condition = counseling.counselingType.eq(CounselingType.PERSONAL).and(counseling.counselingName.contains(skey));
            } else if (sopt.equals("GROUP")) { // 집단 상담 프로그램
                condition = counseling.counselingType.eq(CounselingType.GROUP).and(counseling.counselingName.contains(skey));
            }

            if (condition != null) {
                andBuilder.and(condition);
            }
        }
        /* 검색 처리 E */

        // 검색 조건에 따른 데이터 조회
        List<Counseling> items;
        long totalElements;

        if ("PERSONAL".equals(search.getCounselingType())) { // 개인 상담 프로그램 조회
            Page<PersonalCounseling> pageResult = personalRepository.findAll(andBuilder, pageable);
            items = new ArrayList<>(pageResult.getContent());
            totalElements = pageResult.getTotalElements();
        } else if ("GROUP".equals(search.getCounselingType())) { // 그룹 상담 프로그램 조회
            Page<GroupCounseling> pageResult = groupRepository.findAll(andBuilder, pageable);
            items = new ArrayList<>(pageResult.getContent());
            totalElements = pageResult.getTotalElements();
        } else { // 상담 프로그램 조회 (개인 + 집단)
            Page<Counseling> pageResult = counselingRepository.findAll(andBuilder, pageable);
            items = new ArrayList<>(pageResult.getContent());
            totalElements = pageResult.getTotalElements();
        }

        items.forEach(this::addInfo); // 추가 정보 처리

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Pagination pagination = new Pagination(page, (int) totalElements, 10, limit, request);

        return new ListData<>(items, pagination);
    }

    /**
     * 추가 데이터 처리
     * 1. 예약 가능 요일 : 월 ~ 금 (주중)
     * 2. 예약 가능 시간 : 9시 ~ 18시
     * @param item
     */
    private void addInfo(Counseling item) {
        List<LocalDateTime> availableDates = new ArrayList<>();

        LocalDateTime startDateTime;
        LocalDateTime endDateTime;

        if (item instanceof PersonalCounseling) {
            PersonalCounseling personal = (PersonalCounseling) item;
            startDateTime = personal.getReservationSdate().atTime(LocalTime.of(9, 0));
            endDateTime = personal.getReservationEdate().atTime(LocalTime.of(18, 0));
            logger.info("PersonalCounseling - 시작일시: {}, 종료일시: {}", startDateTime, endDateTime);

        } else if (item instanceof GroupCounseling) {
            GroupCounseling group = (GroupCounseling) item;
            startDateTime = group.getCounselingSdate().atTime(LocalTime.of(9, 0));
            endDateTime = group.getCounselingEdate().atTime(LocalTime.of(18, 0));
            logger.info("GroupCounseling - 시작일시: {}, 종료일시: {}", startDateTime, endDateTime);
        } else {
            throw new IllegalArgumentException("해당하는 상담 유형이 존재하지 않습니다.");
        }

        LocalDateTime currentDateTime = startDateTime;

        while (currentDateTime.isBefore(endDateTime)) {
            if (currentDateTime.toLocalDate().getDayOfWeek() != DayOfWeek.SATURDAY &&
                    currentDateTime.toLocalDate().getDayOfWeek() != DayOfWeek.SUNDAY) {

                LocalTime time = LocalTime.of(9, 0);
                while (time.isBefore(LocalTime.of(18, 0))) {
                    availableDates.add(currentDateTime.with(time));
                    time = time.plusHours(1);
                }
            }
            currentDateTime = currentDateTime.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);
        }
        logger.debug("예약 가능한 날짜 및 시간 목록: {}", availableDates);
    }
}
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
import xyz.sangdam.counseling.constants.Status;
import xyz.sangdam.counseling.controllers.ReservationSearch;
import xyz.sangdam.counseling.entities.QReservation;
import xyz.sangdam.counseling.entities.Reservation;
import xyz.sangdam.counseling.exceptions.ReservationNotFoundException;
import xyz.sangdam.counseling.repositories.ReservationRepository;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Pagination;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Member;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class ReservationInfoService {
    private final ReservationRepository repository;
    private final MemberUtil memberUtil;
    private final HttpServletRequest request;

    /**
     * 개별 조회
     * @param rNo
     * @return
     */
    public Reservation get(Long rNo) {
        BooleanBuilder builder = new BooleanBuilder();
        QReservation reservation = QReservation.reservation;
        builder.and(reservation.rNo.eq(rNo))
                .and(reservation.deletedAt.isNull()); // deletedAt이 null인 것만 조회(소프트 삭제)

        Reservation item = repository.findById(rNo).orElseThrow(ReservationNotFoundException::new);

        // 추가 데이터 처리
        addInfo(item);

        return item;
    }

    /**
     * 목록 조회
     * @param search
     * @return
     */
    public ListData<Reservation> getList(ReservationSearch search) {
        int page = Math.max(search.getPage(), 1); // 페이지가 0이거나 음수이면 기본값 1
        int limit = search.getLimit(); // 한 페이지당 보여줄 레코드 개수
        limit = limit < 1 ? 20 : limit; // 레코드 개수 기본값 20

        /* 검색 처리 S */
        BooleanBuilder andBuilder = new BooleanBuilder();
        QReservation reservation = QReservation.reservation;

        Member member = memberUtil.getMember();
        if (memberUtil.isCounselor() || memberUtil.isProfessor()) { // 상담사나 교수일 때 상담사 이메일로 본인 것만 조회
            andBuilder.and(reservation.counselorEmail.eq(member.getEmail()));
        } else if (memberUtil.isStudent()) { // 학생일 때 학생 이메일로 본인 것만 조회
            andBuilder.and(reservation.email.eq(member.getEmail()));
        }

        // 검색 옵션 및 키워드 처리
        String sopt = search.getSopt(); // 검색 옵션
        String skey = search.getSkey();  // 검색 키워드를 통한 검색

        andBuilder.and(reservation.deletedAt.isNull()); // deletedAt이 null인 것만 조회(소프트 삭제)

        sopt = StringUtils.hasText(sopt) ? sopt.toUpperCase(): "All"; // 통합검색이 기본
        if (StringUtils.hasText(skey)) {
            skey = skey.trim();
            StringExpression expression = null;
            if (sopt.equals("COUNSELING_TYPE")) { // 개인/집단상담 구분
                expression = reservation.counselingType.stringValue();
            } else if (sopt.equals("CATEGORY")) { // 개인상담 분류
                expression = reservation.category.stringValue();
            } else if (sopt.equals("COUNSELING_NAME")) { // 상담명(개인상담 + 집단상담 프로그램명)
                expression = reservation.counselingName;
            } else if (sopt.equals("COUNSELOR")) { // 상담사
                expression = reservation.counselorName.concat(reservation.counselorEmail);
            } else if (sopt.equals("USER")) { // 신청자(학생)
                expression = reservation.userName.concat(reservation.email);
            } else { // 통합 검색
                expression = reservation.counselingType.stringValue()
                        .concat(reservation.category.stringValue())
                        .concat(reservation.counselingName)
                        .concat(reservation.counselorName)
                        .concat(reservation.counselorEmail)
                        .concat(reservation.userName)
                        .concat(reservation.email);
            }

            if (expression != null) {
                andBuilder.and(expression.contains(skey));
            }
        }

        // 진행상태(다중선택 가능)
        List<String> status = search.getStatus();
        if (status != null && !status.isEmpty()) {
            List<Status> _status = status.stream().map(Status::valueOf).toList();
            andBuilder.and(reservation.status.in(_status));
        }

        // 상담일 검색
        LocalDate sDate = search.getSDate();
        LocalDate eDate = search.getEDate();
        if (sDate != null) {
            andBuilder.and(reservation.rDateTime.goe(sDate.atStartOfDay())); // atStartOfDay(하루의 시작 시간)
        }

        if (eDate != null) {
            andBuilder.and(reservation.rDateTime.loe(eDate.atTime(LocalTime.MAX))); // LocalDate에 시간 추가 - atTime, LocalTime.MAX(하루의 마지막 시간)
        }
        /* 검색 처리 E */

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt"))); // page -1 (pageable 첫번째 페이지 0부터 시작)
        Page<Reservation> data = repository.findAll(andBuilder, pageable);

        long total = repository.count(andBuilder);
        Pagination pagination = new Pagination(page, (int)total, 10, limit, request);

        List<Reservation> items = data.getContent();
        if (items != null && !items.isEmpty()) {
            items.forEach(this::addInfo);
        }

        return new ListData<>(items, pagination);
    }

    private void addInfo(Reservation item) {

    }

    
}
package xyz.sangdam.counseling.services;

import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.controllers.ReservationSearch;
import xyz.sangdam.counseling.entities.QReservation;
import xyz.sangdam.counseling.entities.Reservation;
import xyz.sangdam.counseling.exceptions.ReservationNotFoundException;
import xyz.sangdam.counseling.repositories.ReservationRepository;
import xyz.sangdam.global.ListData;
import xyz.sangdam.global.Pagination;
import xyz.sangdam.member.MemberUtil;
import xyz.sangdam.member.entities.Member;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class ReservationInfoService {
    private final ReservationRepository reservationRepository;
    private final MemberUtil memberUtil;
    private final HttpServletRequest request;

    public Reservation get(Long rNo) {
        Reservation item = reservationRepository.findById(rNo).orElseThrow(ReservationNotFoundException::new);

        // 추가 데이터 처리
        addInfo(item);

        return item;
    }

    public ListData<Reservation> getList(ReservationSearch search) {
        int page = Math.max(search.getPage(), 1); // 페이지가 0이거나 음수이면 기본값 1
        int limit = search.getLimit(); // 한 페이지당 보여줄 레코드 개수
        limit = limit < 1 ? 10 : limit; // 레코드 개수 기본값 10
        int offset = (page -1) * limit; // 레코드 시작 위치 구하기

        BooleanBuilder andBuilder = new BooleanBuilder();
        QReservation reservation = QReservation.reservation;
        Member member = memberUtil.getMember();
        if (memberUtil.isCounselor() || memberUtil.isProfessor()) { // 상담사나 교수일 때 상담사 이메일로 본인 것만 조회
            andBuilder.and(reservation.counselorEmail.eq(member.getEmail()));
        } else if (memberUtil.isStudent()) { // 학생일 때 학생 이메일로 본인 것만 조회
            andBuilder.and(reservation.email.eq(member.getEmail()));
        }

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt"))); // page -1 (pageable 첫번째 페이지 0부터 시작)
        Page<Reservation> data = reservationRepository.findAll(andBuilder, pageable);

        long total = reservationRepository.count(andBuilder);
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
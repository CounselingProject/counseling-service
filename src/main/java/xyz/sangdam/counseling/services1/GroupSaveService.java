package xyz.sangdam.counseling.services1;

import org.springframework.util.StringUtils;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.controllers.RequestCounseling;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.entities.GroupCounseling;
import xyz.sangdam.counseling.entities.PersonalCounseling;
import xyz.sangdam.counseling.repositories.GroupCounselingRepository;
import xyz.sangdam.counseling.repositories.PersonalCounselingRepository;

public class GroupSaveService {

private final PersonalCounselingRepository personalRepository;
private final GroupCounselingRepository groupRepository;

public void save(RequestCounseling form) {

    Long cNo = form.getCounselingNo();
    String type = form.getCounselingType();
    type = StringUtils.hasText(type) ? type : "PERSONAL";
    CounselingType counselingType = CounselingType.valueOf(type);

    Counseling counseling = null;
    if (counselingType == CounselingType.GROUP) {
        counseling = cNo == null ? new GroupCounseling() : groupRepository.findById(cNo).orElseGet(GroupCounseling::new);
    } else {
        counseling = cNo == null ? new PersonalCounseling() : personalRepository.findById(cNo).orElseGet(PersonalCounseling::new);
    }

    /* 공통 항목 처리 S */

    counseling.setCounselingType(CounselingType.valueOf(form.getCounselingType()));
    counseling.setCounselingName(form.getCounselingName());
    counseling.setCounsellingDes(form.getCounsellingDes());
    counseling.setCounselorName(form.getCounselorName());
    counseling.setReservationSdate(form.getReservationSdate());
    counseling.setReservationEdate(form.getReservationEdate());


    /* 공통 항목 처리 E */

    if (counseling instanceof GroupCounseling groupCounseling) {
        // 집단 상담에 추가할 처리
        groupCounseling.setCounselingSdate(form.getCounselingSdate()); // 상담시작일
        groupCounseling.setCounselingEdate(form.getCounselingEdate()); // 상담종료일
        groupCounseling.setCounselingLimit(form.getCounselingLimit()); // 인원수
        groupCounseling.setGid(form.getGid()); // 홍보이미지 파일업로드


        groupRepository.saveAndFlush(groupCounseling);
    } else if (counseling instanceof PersonalCounseling personalCounseling) {
        // 개별 상담에 추가할 처리

        personalCounseling.setCategory(form.getCategory());

        personalRepository.saveAndFlush(personalCounseling);
    }
}
}

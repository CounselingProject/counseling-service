package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.sangdam.counseling.constants.CounselingType;
import xyz.sangdam.counseling.controllers.RequestCounseling;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.entities.GroupCounseling;
import xyz.sangdam.counseling.entities.PersonalCounseling;
import xyz.sangdam.counseling.repositories.GroupCounselingRepository;
import xyz.sangdam.counseling.repositories.PersonalCounselingRepository;

@Service
@RequiredArgsConstructor
public class CounselingSaveService {
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

        /* 공통 항목 처리 E */

        if (counseling instanceof GroupCounseling groupCounseling) {
            // 집단 상담에 추가할 처리

            groupRepository.saveAndFlush(groupCounseling);
        } else if (counseling instanceof PersonalCounseling personalCounseling) {
            // 개별 상담에 추가할 처리

            personalRepository.saveAndFlush(personalCounseling);
        }
    }
}
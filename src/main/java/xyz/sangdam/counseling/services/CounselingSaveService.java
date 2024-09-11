package xyz.sangdam.counseling.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.counseling.repositories.GroupCounselingRepository;
import xyz.sangdam.counseling.repositories.PersonalCounselingRepository;

@Service
@RequiredArgsConstructor
public class CounselingSaveService {
    private final PersonalCounselingRepository personalRepository;
    private final GroupCounselingRepository groupRepository;
}
package xyz.sangdam.group;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.sangdam.counseling.controllers.RequestCounseling;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.counseling.services.CounselingSaveService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class RegisterTest {

    @Autowired
    private CounselingSaveService saveService;

    @Autowired
    private CounselingRepository repository;

    private RequestCounseling form;

    @Autowired
    private ObjectMapper om;

    @BeforeEach
    void init() {
        form = new RequestCounseling();
        form.setCNo(1L);
        form.setCounselingName("자존감 회복 프로그램");
        form.setCounselingDes("대학교 생활을 하면서 교우관계 및 성적으로 인한 자존감 하락한 학생들 대상");
        form.setMode("write");
        form.setGid("13231312");
        form.setCounselorName("안재모");
        form.setCounselorEmail("abcde@gmail.com");
        form.setReservationSdate(LocalDate.of(2024, 9, 18));
        form.setReservationSdate(LocalDate.of(2024, 9, 20));
        form.setCounselingLimit(10);
        form.setCounselingDate(LocalDateTime.of(2024,9,29,14,30));

    }

    @Test
    void saveTest() {
        saveService.save(form);
        //RequestCounseling saved = repository.findById(form.getCNo()).orElse(null);
    }

}

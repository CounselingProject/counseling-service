package xyz.sangdam.group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.sangdam.counseling.entities.Counseling;
import xyz.sangdam.counseling.repositories.CounselingRepository;
import xyz.sangdam.counseling.services.CounselingSaveService;

@SpringBootTest
public class registerTest {

    @Autowired
    private CounselingSaveService saveService;

    @Autowired
    private CounselingRepository repository;

    private Counseling counseling;

    @BeforeEach
    void init() {
        counseling = new Counseling();
        /*counseling.setCNo(1L);
        counseling.setCounselingName("자아를 찾기 위한 프로그램");
        */
      repository.saveAndFlush(counseling);
    }

    @Test
    void saveTest() {

        Counseling form = new Counseling();
        form.setCNo(1L);
        form.setCounselingName("내 자아를 찾기 위한 프로그램");
        //form.setReservationSdate();
        form.setCounselingDes("자아가 분열됐을 때 다시 찾기 위해서 심리 집단 상담 프로그램");



        System.out.println(form);
    }


}

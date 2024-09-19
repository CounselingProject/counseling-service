package xyz.sangdam.group;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.sangdam.counseling.services.CounselingDeleteService;

@SpringBootTest
public class DeleteTest {


    @Autowired
    private CounselingDeleteService deleteService;




    @Test
    void deleteTest() {
        Long cNo = 1L;
        deleteService.delete(1L);

    }
}

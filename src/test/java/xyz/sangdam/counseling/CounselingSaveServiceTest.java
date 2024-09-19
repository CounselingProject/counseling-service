package xyz.sangdam.counseling;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.sangdam.counseling.controllers.RequestCounseling;
import xyz.sangdam.counseling.services.CounselingSaveService;

@SpringBootTest
public class CounselingSaveServiceTest {

    @Autowired
    private CounselingSaveService saveService;

    @Test
    void save() {
        RequestCounseling form = new RequestCounseling();

    }
}

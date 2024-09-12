package xyz.sangdam.counseling.entities1;

import jakarta.persistence.*;
import lombok.Data;
import xyz.sangdam.counseling.entities.GroupCounseling;

import java.time.LocalDate;

@Entity
@Data
public class GroupSchedule {

    @Id
    @GeneratedValue
    private Long scheduleNo;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="counselingNo")
    private GroupCounseling counseling;

    private LocalDate counselingDate; // 상담일

    @Lob
    private String record; // 상담 일지(집단상담별)
}

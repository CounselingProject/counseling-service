package xyz.sangdam.counseling.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class GroupReservation extends Reservation {

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupSchedule schedule;
}
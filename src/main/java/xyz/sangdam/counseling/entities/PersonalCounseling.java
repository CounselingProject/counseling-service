package xyz.sangdam.counseling.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import xyz.sangdam.counseling.constants.Category;

@Data
@Entity
public class PersonalCounseling extends Counseling {

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private Category category; // PROFESSOR 교수 상담, EMPLOYMENT 취업 상담, PSYCHOLOGICAL 심리 상담
}
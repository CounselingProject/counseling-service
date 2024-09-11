package xyz.sangdam.counseling.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import xyz.sangdam.counseling.constants.PersonalCategory;

@Data
@Entity
public class PersonalCounseling extends Counseling {

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private PersonalCategory category;
}
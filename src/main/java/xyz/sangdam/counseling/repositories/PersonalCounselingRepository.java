package xyz.sangdam.counseling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.sangdam.counseling.entities.PersonalCounseling;

public interface PersonalCounselingRepository extends JpaRepository<PersonalCounseling, Long>, QuerydslPredicateExecutor<PersonalCounseling> {
}
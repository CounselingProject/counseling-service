package xyz.sangdam.counseling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.sangdam.counseling.entities.Counseling;

public interface CounselingRepository extends JpaRepository<Counseling, Long>, QuerydslPredicateExecutor<Counseling> {
}
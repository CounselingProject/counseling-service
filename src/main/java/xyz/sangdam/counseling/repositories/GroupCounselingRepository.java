package xyz.sangdam.counseling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.sangdam.counseling.entities.GroupCounseling;

public interface GroupCounselingRepository extends JpaRepository<GroupCounseling, Long>, QuerydslPredicateExecutor<GroupCounseling> {
}
package xyz.sangdam.counseling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.sangdam.counseling.entities.GroupSchedule;

public interface GroupScheduleRepository extends JpaRepository<GroupSchedule, Long>, QuerydslPredicateExecutor<GroupSchedule> {
}
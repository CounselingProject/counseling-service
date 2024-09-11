package xyz.sangdam.counseling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.sangdam.counseling.entities.GroupReservation;

public interface GroupReservationRepository extends JpaRepository<GroupReservation, Long>, QuerydslPredicateExecutor<GroupReservation> {
}
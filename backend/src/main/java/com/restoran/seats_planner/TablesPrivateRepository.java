package com.restoran.seats_planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablesPrivateRepository extends JpaRepository<TablesPrivate, Long> {
    List<TablesPrivate> findByDate(String date);
}

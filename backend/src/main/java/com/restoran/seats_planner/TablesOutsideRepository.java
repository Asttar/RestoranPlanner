package com.restoran.seats_planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablesOutsideRepository extends JpaRepository<TablesOutside, Long> {
    List<TablesOutside> findByDate(String date);
}

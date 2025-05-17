package com.recicla_mais.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recicla_mais.app.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}

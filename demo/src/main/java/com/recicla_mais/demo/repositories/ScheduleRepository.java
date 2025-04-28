package com.recicla_mais.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recicla_mais.demo.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}

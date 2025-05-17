package com.recicla_mais.app.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.recicla_mais.app.models.Schedule;
import com.recicla_mais.app.repositories.ScheduleRepository;

@Service
public class ScheduleService {
  private final ScheduleRepository scheduleRepository;

  public ScheduleService(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }
  
  public Schedule createSchedule(Schedule schedule) {
    return scheduleRepository.save(schedule);
  }

  public Optional<Schedule> getScheduleById(UUID id) {
    return scheduleRepository.findById(id);
  }

  public List<Schedule> getAllSchedules() {
    return scheduleRepository.findAll();
  }

  public Schedule updateSchedule(UUID id, Schedule schedule) {
    if (!scheduleRepository.existsById(id)) {
      return null;
    }
    
    schedule.setId(id);
    
    return scheduleRepository.save(schedule);
  }

  public void deleteSchedule(UUID id) {
    scheduleRepository.deleteById(id);
  }
}

package com.recicla_mais.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recicla_mais.demo.models.Schedule;
import com.recicla_mais.demo.repositories.ScheduleRepository;

@Service
public class ScheduleService {
  private final ScheduleRepository scheduleRepository;

  public ScheduleService(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }
  
  public Schedule createSchedule(Schedule schedule) {
    return scheduleRepository.save(schedule);
  }

  public Optional<Schedule> getScheduleById(Long id) {
    return scheduleRepository.findById(id);
  }

  public List<Schedule> getAllSchedules() {
    return scheduleRepository.findAll();
  }

  public Schedule updateSchedule(Long id, Schedule schedule) {
    if (!scheduleRepository.existsById(id)) {
      return null;
    }
    
    schedule.setId(id);
    
    return scheduleRepository.save(schedule);
  }

  public void deleteSchedule(Long id) {
    scheduleRepository.deleteById(id);
  }
}

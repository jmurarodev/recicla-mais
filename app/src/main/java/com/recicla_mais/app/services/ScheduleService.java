package com.recicla_mais.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

  public Schedule getScheduleById(UUID id) {
    return scheduleRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado.")
      );
  }

  public List<Schedule> getAllSchedules() {
    return scheduleRepository.findAll();
  }

  public Schedule updateSchedule(UUID id, Schedule schedule) {
      Schedule existingSchedule = scheduleRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado.")
      );

      if (schedule.getDateTime() != null) {
        existingSchedule.setDateTime(schedule.getDateTime());
      }
      
      if (schedule.getStreet() != null) {
        existingSchedule.setStreet(schedule.getStreet());
      }
      
      if (schedule.getNumber() != null) {
        existingSchedule.setNumber(schedule.getNumber());
      }
      
      if (schedule.getNeighborhood() != null) {
        existingSchedule.setNeighborhood(schedule.getNeighborhood());
      }
      
      if (schedule.getCity() != null) {
        existingSchedule.setCity(schedule.getCity());
      }

      if (schedule.getShortState() != null) {
        existingSchedule.setShortState(schedule.getShortState());
      }

      if (schedule.getStatus() != null) {
        existingSchedule.setStatus(schedule.getStatus());
      }
      
      existingSchedule.setId(id);
      
      return scheduleRepository.save(existingSchedule);
  }

  public void deleteSchedule(UUID id) {
      Schedule schedule = scheduleRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Agendamento com ID " + id + " não encontrado.")
      );
      
      scheduleRepository.delete(schedule);
  }
}

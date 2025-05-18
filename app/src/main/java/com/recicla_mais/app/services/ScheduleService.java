package com.recicla_mais.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.recicla_mais.app.models.Client;
import com.recicla_mais.app.models.Operator;
import com.recicla_mais.app.models.Schedule;
import com.recicla_mais.app.repositories.ClientRepository;
import com.recicla_mais.app.repositories.OperatorRepository;
import com.recicla_mais.app.repositories.ScheduleRepository;

@Service
public class ScheduleService {
  private final ScheduleRepository scheduleRepository;
  private final ClientRepository clientRepository;
  private final OperatorRepository operatorRepository;

  public ScheduleService(ScheduleRepository scheduleRepository, ClientRepository clientRepository, OperatorRepository operatorRepository) {
    this.scheduleRepository = scheduleRepository;
    this.clientRepository = clientRepository;
    this.operatorRepository = operatorRepository;
  }
  
  public Schedule createSchedule(Schedule schedule) {
    UUID clientId = schedule.getClient() != null ? schedule.getClient().getId() : null;

    if (clientId == null) {
      throw new ResourceNotFoundException("O ID do cliente é obrigatório");
    }

    Client client = clientRepository.findById(clientId).orElseThrow(() ->
      new ResourceNotFoundException("Cliente não encontrado com ID: " + clientId));

    schedule.setClient(client);

    if (schedule.getOperator() != null && schedule.getOperator().getId() != null) {
        UUID operatorId = schedule.getOperator().getId();
        
        Operator operator = operatorRepository.findById(operatorId)
            .orElseThrow(() -> new ResourceNotFoundException("Operador não encontrado com ID: " + operatorId));
        schedule.setOperator(operator);
    }

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

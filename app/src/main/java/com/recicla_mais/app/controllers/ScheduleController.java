package com.recicla_mais.app.controllers;

import com.recicla_mais.app.models.Schedule;
import com.recicla_mais.app.services.ScheduleService;

import jakarta.validation.Valid;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable UUID id) {
        try {
            Schedule schedule = scheduleService.getScheduleById(id);
            return ResponseEntity.ok(schedule);
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    @PostMapping
    public ResponseEntity<?> createSchedule(@Valid @RequestBody Schedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("errors", errors);

            return ResponseEntity.badRequest().body(body);
        }

        try {
            Schedule savedSchedule = scheduleService.createSchedule(schedule);
            return ResponseEntity.ok(savedSchedule);
        }  catch (Exception ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable UUID id, @RequestBody Schedule schedule) {
        try {
            Schedule updatedSchedule = scheduleService.updateSchedule(id, schedule);

            return ResponseEntity.ok(updatedSchedule);
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable UUID id) {
        try {
            scheduleService.deleteSchedule(id);

            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}

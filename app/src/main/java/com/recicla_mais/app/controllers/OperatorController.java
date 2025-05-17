package com.recicla_mais.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recicla_mais.app.models.Operator;
import com.recicla_mais.app.services.OperatorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/operators")
public class OperatorController {
    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping
    public List<Operator> getAllOperators() {
        return operatorService.getAllOperators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOperatorById(@PathVariable UUID id) {
        try {
            Operator operator = operatorService.getOperatorById(id);
            return ResponseEntity.ok(operator);
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createOperator(@Valid @RequestBody Operator operator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("errors", errors);

            return ResponseEntity.badRequest().body(body);
        }

        try {
            Operator savedOperator = operatorService.createOperator(operator);
            return ResponseEntity.ok(savedOperator);
        }  catch (Exception ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOperator(@PathVariable UUID id, @RequestBody Operator operator) {
        try {
            Operator updatedOperator = operatorService.updateOperator(id, operator);

            return ResponseEntity.ok(updatedOperator);
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOperator(@PathVariable UUID id) {
        try {
            operatorService.deleteOperator(id);

            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}

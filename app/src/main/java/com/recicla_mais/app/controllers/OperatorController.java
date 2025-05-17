package com.recicla_mais.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Operator> getOperatorById(@PathVariable Long id) {
        Optional<Operator> operator = operatorService.getOperatorById(id);
        return operator.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Operator createOperator(@RequestBody Operator operator) {
        return operatorService.createOperator(operator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operator> updateOperator(@PathVariable Long id, @RequestBody Operator operator) {
        Operator updatedOperator = operatorService.updateOperator(id, operator);

        if (updatedOperator == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedOperator);
    }

    @DeleteMapping("/{id}")
    public void deleteOperator(@PathVariable Long id) {
        operatorService.deleteOperator(id);
    }
}

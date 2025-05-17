package com.recicla_mais.app.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.recicla_mais.app.models.Operator;
import com.recicla_mais.app.repositories.OperatorRepository;

@Service
public class OperatorService {
  private final OperatorRepository operatorRepository;

  public OperatorService(OperatorRepository operatorRepository) {
    this.operatorRepository = operatorRepository;
  }

  public Operator createOperator(Operator operator) {
    return operatorRepository.save(operator);
  }

  public Optional<Operator> getOperatorById(UUID id) {
    return operatorRepository.findById(id);
  }

  public List<Operator> getAllOperators() {
    return operatorRepository.findAll();
  }

  public Operator updateOperator(UUID id, Operator operator) {
    if (!operatorRepository.existsById(id)) {
      return null;
    }
    
    operator.setId(id);
    
    return operatorRepository.save(operator);
  }

  public void deleteOperator(UUID id) {
    operatorRepository.deleteById(id);
  }
}

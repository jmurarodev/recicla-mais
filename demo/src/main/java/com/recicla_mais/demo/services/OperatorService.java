package com.recicla_mais.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recicla_mais.demo.models.Operator;
import com.recicla_mais.demo.repositories.OperatorRepository;

@Service
public class OperatorService {
  private final OperatorRepository operatorRepository;

  public OperatorService(OperatorRepository operatorRepository) {
    this.operatorRepository = operatorRepository;
  }

  public Operator createOperator(Operator operator) {
    return operatorRepository.save(operator);
  }

  public Optional<Operator> getOperatorById(Long id) {
    return operatorRepository.findById(id);
  }

  public List<Operator> getAllOperators() {
    return operatorRepository.findAll();
  }

  public Operator updateOperator(Long id, Operator operator) {
    if (!operatorRepository.existsById(id)) {
      return null;
    }
    
    operator.setId(id);
    
    return operatorRepository.save(operator);
  }

  public void deleteOperator(Long id) {
    operatorRepository.deleteById(id);
  }
}

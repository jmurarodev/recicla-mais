package com.recicla_mais.app.services;
import java.util.List;
import java.util.UUID;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

  public Operator getOperatorById(UUID id) {
    return operatorRepository.findById(id).orElseThrow(() ->
      new ResourceNotFoundException("Operador com ID " + id + " não encontrado.")
    );
  }

  public List<Operator> getAllOperators() {
    return operatorRepository.findAll();
  }

  public Operator updateOperator(UUID id, Operator operator) {
    Operator existingOperator = operatorRepository.findById(id).orElseThrow(() ->
      new ResourceNotFoundException("Operador com ID " + id + " não encontrado.")
    );

    if (operator.getName() != null) {
      existingOperator.setName(operator.getName());
    }
    
    if (operator.getEmail() != null) {
      existingOperator.setEmail(operator.getEmail());
    }
    
    if (operator.getPassword() != null) {
      existingOperator.setPassword(operator.getPassword());
    }
    
    if (operator.getDocument() != null) {
      existingOperator.setDocument(operator.getDocument());
    }
    
    if (operator.getDocumentType() != null) {
      existingOperator.setDocumentType(operator.getDocumentType());
    }

    if (operator.getPhoneNumber() != null) {
      existingOperator.setPhoneNumber(operator.getPhoneNumber());
    }
    
    existingOperator.setId(id);
    
    return operatorRepository.save(existingOperator);
  }

  public void deleteOperator(UUID id) {
    Operator operator = operatorRepository.findById(id).orElseThrow(() ->
      new ResourceNotFoundException("Operador com ID " + id + " não encontrado.")
    );
    
    operatorRepository.delete(operator);
  }
}

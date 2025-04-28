package com.recicla_mais.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recicla_mais.demo.models.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
  // Custom query methods can be defined here if needed
  // For example, to find operators by name or email, you can add methods like:
  // List<Operator> findByName(String name);
  // Operator findByEmail(String email);
  // List<Operator> findByDocument(String document);
  // List<Operator> findByDocumentType(String documentType);
  // List<Operator> findByPhoneNumber(String phoneNumber);
}

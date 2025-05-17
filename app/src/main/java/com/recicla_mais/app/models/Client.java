package com.recicla_mais.app.models;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Client {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
  @JdbcTypeCode(SqlTypes.CHAR)
  private UUID id;
  @NotBlank(message = "O nome é obrigatório")
  private String name;
  @NotBlank(message = "O email é obrigatório")
  private String email;
  @NotBlank(message = "O password é obrigatório")
  private String password;
  @NotBlank(message = "O documento é obrigatório")
  private String document;
  @NotBlank(message = "O tipo de documento é obrigatório")
  private String documentType;
  @NotBlank(message = "O número de telefone é obrigatório")
  private String phoneNumber;
  private final Boolean isAdmin = false;

  public Client() {}

  // Getters and Setters
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) throws IllegalArgumentException {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Boolean getIsAdmin() {
    return isAdmin;
  }
}

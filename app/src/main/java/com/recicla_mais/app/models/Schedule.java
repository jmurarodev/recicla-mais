package com.recicla_mais.app.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Schedule {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
  @JdbcTypeCode(SqlTypes.CHAR)
  private UUID id;
  @NotBlank(message = "A data é obrigatória")
  private LocalDateTime dateTime;
  @NotBlank(message = "A rua é obrigatória")
  private String street;
  @NotBlank(message = "O número é obrigatório")
  private String number;
  @NotBlank(message = "O bairro é obrigatório")
  private String neighborhood;
  @NotBlank(message = "A cidade é obrigatória")
  private String city;
  @NotBlank(message = "O Estado é obrigatório")
  private String shortState;
  @NotBlank(message = "O CEP obrigatória")
  private String zipCode;
  private String status;

  @ManyToOne
  private Client client;

  @ManyToOne
  private Operator operator;

  public Schedule() {}

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getShortState() {
    return shortState;
  }

  public void setShortState(String shortState) {
    this.shortState = shortState;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }
}

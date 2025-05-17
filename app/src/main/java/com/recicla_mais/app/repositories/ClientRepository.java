package com.recicla_mais.app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recicla_mais.app.models.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}

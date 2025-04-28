package com.recicla_mais.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recicla_mais.demo.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

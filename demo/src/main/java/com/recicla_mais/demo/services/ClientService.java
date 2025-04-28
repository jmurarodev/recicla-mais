package com.recicla_mais.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.recicla_mais.demo.models.Client;
import com.recicla_mais.demo.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
      this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
      return clientRepository.save(client);
    }

    public Optional<Client> getClientById(Long id) {
      return clientRepository.findById(id);
    }

    public List<Client> getAllClients() {
      return clientRepository.findAll();
    }

    public Client updateClient(Long id, Client client) {
      if (!clientRepository.existsById(id)) {
        return null;
      }
      
      client.setId(id);
      
      return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
      clientRepository.deleteById(id);
    }
}

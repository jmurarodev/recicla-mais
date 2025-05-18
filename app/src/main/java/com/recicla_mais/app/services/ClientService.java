package com.recicla_mais.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.recicla_mais.app.models.Client;
import com.recicla_mais.app.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
      this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
      return clientRepository.save(client);
    }

    public Client getClientById(UUID id) {
      return clientRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Cliente com ID " + id + " não encontrado.")
      );
    }

    public List<Client> getAllClients() {
      return clientRepository.findAll();
    }

    public Client updateClient(UUID id, Client client) {
      Client existingClient = clientRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Cliente com ID " + id + " não encontrado.")
      );

      if (client.getName() != null) {
        existingClient.setName(client.getName());
      }
      
      if (client.getEmail() != null) {
        existingClient.setEmail(client.getEmail());
      }
      
      if (client.getPassword() != null) {
        existingClient.setPassword(client.getPassword());
      }
      
      if (client.getDocument() != null) {
        existingClient.setDocument(client.getDocument());
      }
      
      if (client.getDocumentType() != null) {
        existingClient.setDocumentType(client.getDocumentType());
      }

      if (client.getPhoneNumber() != null) {
        existingClient.setPhoneNumber(client.getPhoneNumber());
      }
      
      existingClient.setId(id);
      
      return clientRepository.save(existingClient);
    }

    public void deleteClient(UUID id) {
      Client client = clientRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Cliente com ID " + id + " não encontrado.")
      );
      
      clientRepository.delete(client);
    }
}

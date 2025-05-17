package com.recicla_mais.app.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recicla_mais.app.models.Client;
import com.recicla_mais.app.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable UUID id) {
        try {
            Client client = clientService.getClientById(id);
            return ResponseEntity.ok(client);
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("errors", errors);

            return ResponseEntity.badRequest().body(body);
        }

        try {
            Client savedClient = clientService.createClient(client);
            return ResponseEntity.ok(savedClient);
        }  catch (Exception ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable UUID id, @RequestBody Client client) {
        try {
            Client updatedClient = clientService.updateClient(id, client);

            return ResponseEntity.ok(updatedClient);
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable UUID id) {
        try {
            clientService.deleteClient(id);

            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException ex) {
            Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", ex.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}

package com.example.demo.layers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.ValidacaoException;
import com.example.demo.layers.entities.Cliente;
import com.example.demo.layers.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Endpoint para cadastrar um novo cliente
    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.cadastrarCliente(cliente);
            return ResponseEntity.status(HttpStatus.OK).body("{}");
        } catch (ValidacaoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
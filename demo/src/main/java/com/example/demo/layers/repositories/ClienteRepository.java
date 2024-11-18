package com.example.demo.layers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.layers.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
    List<Cliente> getClientePorNome(String nome);

    List<Cliente> findByNomeContains(String string);

}
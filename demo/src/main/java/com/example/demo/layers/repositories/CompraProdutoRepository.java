package com.example.demo.layers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.layers.entities.CompraProduto;

@Repository
public interface CompraProdutoRepository extends JpaRepository<CompraProduto, Long> {
}
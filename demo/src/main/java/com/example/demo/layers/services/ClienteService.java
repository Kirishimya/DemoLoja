package com.example.demo.layers.services;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ValidacaoException;
import com.example.demo.layers.entities.Cliente;
import com.example.demo.layers.repositories.ClienteRepository;
import com.example.demo.utils.CPFUtils;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(Cliente cliente) throws ValidacaoException {

        if (!CPFUtils.isValidCPF(cliente.getCpf())) {
            throw new ValidacaoException("CPF inválido");
        }

        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new ValidacaoException("Nome inválido");
        }

        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Cliente cliente) throws ValidacaoException {
        // Verificar se o ID do cliente não é nulo
        if (cliente.getId() == null) {
            throw new ValidacaoException("O ID do cliente não pode ser nulo.");
        }

        // Verificar se o cliente com o ID informado existe no sistema
        Cliente clienteExistente = clienteRepository.findById(cliente.getId()).get();
        if (clienteExistente == null) {
            throw new ValidacaoException("Cliente não encontrado com o ID fornecido.");
        }

        // Caso o cliente exista, realizar a atualização (a lógica de atualização pode variar)
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new ValidacaoException("Nome inválido");
        }

        // Adicione aqui outros campos que precisam ser atualizados
        if (!CPFUtils.isValidCPF(cliente.getCpf())) {
            throw new ValidacaoException("CPF inválido");
        }

        // Converter dataNascimento (Date) para LocalDate
        if (cliente.getDataNascimento() == null) {
            throw new ValidacaoException("A data de nascimento do cliente não pode ser nula.");
        }

        LocalDate dataNascimento = cliente.getDataNascimento().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Verificar se a dataNascimento é válida (não pode ser no futuro)
        LocalDate hoje = LocalDate.now();
        if (dataNascimento.isAfter(hoje)) {
            throw new ValidacaoException("A data de nascimento não pode ser no futuro.");
        }

        // Verificar se a data de nascimento do cliente não é inválida (por exemplo, cliente com idade muito baixa)
        if (dataNascimento.isAfter(hoje.minusYears(18))) {
            throw new ValidacaoException("O cliente deve ter pelo menos 18 anos.");
        }

        //Atualiza dados
        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setDataNascimento(cliente.getDataNascimento());

        // Salvar o cliente atualizado
        clienteRepository.save(clienteExistente);

        // Retornar o cliente atualizado
        return clienteExistente;
    }

}

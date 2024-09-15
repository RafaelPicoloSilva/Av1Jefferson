package com.av1.vendas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av1.vendas.domains.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID>{

    Optional<Client> findByCpf(String cpf);
    Optional<Client> findByEmail(String email);
}

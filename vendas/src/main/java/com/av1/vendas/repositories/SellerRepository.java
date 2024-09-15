package com.av1.vendas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av1.vendas.domains.Seller;
@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID>{

    Optional<Seller> findByCpf(String cpf);
    Optional<Seller> findByEmail(String email);
}

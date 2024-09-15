package com.av1.vendas.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.av1.vendas.domains.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{

    Optional<Product> findByDescription(String description);

}

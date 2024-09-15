package com.av1.vendas.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av1.vendas.domains.Sale;
@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID>{

}

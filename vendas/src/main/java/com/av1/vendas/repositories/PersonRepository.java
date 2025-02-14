package com.av1.vendas.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av1.vendas.domains.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person, UUID>{

}

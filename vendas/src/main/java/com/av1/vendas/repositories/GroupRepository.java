package com.av1.vendas.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.av1.vendas.domains.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID>{

    Optional<Group> findByDescription(String description);

}

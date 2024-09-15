package com.av1.vendas.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.av1.vendas.domains.dtos.ClientDTO;
import com.av1.vendas.domains.enums.PersonType;
import com.av1.vendas.domains.enums.PersonStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends Person{

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Sale> sales = new ArrayList<>();

    public Client(UUID id, String firstName, String lastName, String cpf, String email, String password){
        super(id, firstName, lastName, cpf, email, password);
        addPersonType(PersonType.ADMIN);
        addPersonStatus(PersonStatus.ACTIVE);
    }

    public Client(ClientDTO obj){
        this.setId(obj.getId());
        this.setFirstName(obj.getFirstName());
        this.setLastName(obj.getLastName());
        this.setCpf(obj.getCpf());
        this.setEmail(obj.getEmail());
        this.setPassword(obj.getPassword());
    this.birthDate = obj.getBirthDate();
    this.personType = obj.getPersonType().stream().map(x -> x.getId()).collect(Collectors.toSet());
    this.personStatus = obj.getPersonStatus().stream().map(x -> x.getId()).collect(Collectors.toSet());
    addPersonType(PersonType.ADMIN);
    addPersonType(PersonType.CLIENT);
    addPersonType(PersonType.SELLER);
    addPersonStatus(PersonStatus.ACTIVE);
    addPersonStatus(PersonStatus.INATIVE);
}


public Client(){
super();
addPersonType(PersonType.ADMIN);
addPersonStatus(PersonStatus.ACTIVE);
}

public List<Sale> getSales(){
    return sales;
}

public void setSales(List<Sale> sales) {
    this.sales = sales;
}
    
}

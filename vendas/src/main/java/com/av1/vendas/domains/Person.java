package com.av1.vendas.domains;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import com.av1.vendas.domains.enums.PersonStatus;
import com.av1.vendas.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "person")
public abstract class Person {

     @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate birthDate = LocalDate.now();

    @Column(unique = true)
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    protected Set<Integer> personType = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "status")
    protected Set<Integer> personStatus = new HashSet<>();

public Person(){
    super();
    addPersonType(PersonType.ADMIN);
    addPersonStatus(PersonStatus.ACTIVE);
}

public Person(UUID id, String firstName, String lastName, String cpf, String email, String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.cpf = cpf;
    this.email = email;
    this.password = password;
    addPersonType(PersonType.ADMIN);
    addPersonStatus(PersonStatus.ACTIVE);
}

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getCpf() {
    return cpf;
}

public void setCpf(String cpf) {
    this.cpf = cpf;
}

public LocalDate getBirthDate() {
    return birthDate;
}

public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public Set<PersonType> getPersonType() {
    return personType.stream().map(x -> PersonType.toEnum(x)).collect(Collectors.toSet());
}

public void addPersonType(PersonType personType) {
    this.personType.add(personType.getId());
}

public Set<PersonStatus> getPersonStatus() {
    return personStatus.stream().map(x -> PersonStatus.toEnum(x)).collect(Collectors.toSet());
}

public void addPersonStatus(PersonStatus personStatus) {
    this.personStatus.add(personStatus.getId());
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
    result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((personType == null) ? 0 : personType.hashCode());
    result = prime * result + ((personStatus == null) ? 0 : personStatus.hashCode());
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Person other = (Person) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    if (firstName == null) {
        if (other.firstName != null)
            return false;
    } else if (!firstName.equals(other.firstName))
        return false;
    if (lastName == null) {
        if (other.lastName != null)
            return false;
    } else if (!lastName.equals(other.lastName))
        return false;
    if (cpf == null) {
        if (other.cpf != null)
            return false;
    } else if (!cpf.equals(other.cpf))
        return false;
    if (birthDate == null) {
        if (other.birthDate != null)
            return false;
    } else if (!birthDate.equals(other.birthDate))
        return false;
    if (email == null) {
        if (other.email != null)
            return false;
    } else if (!email.equals(other.email))
        return false;
    if (password == null) {
        if (other.password != null)
            return false;
    } else if (!password.equals(other.password))
        return false;
    if (personType == null) {
        if (other.personType != null)
            return false;
    } else if (!personType.equals(other.personType))
        return false;
    if (personStatus == null) {
        if (other.personStatus != null)
            return false;
    } else if (!personStatus.equals(other.personStatus))
        return false;
    return true;
}




    
}

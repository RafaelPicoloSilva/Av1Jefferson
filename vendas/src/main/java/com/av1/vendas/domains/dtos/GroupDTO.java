package com.av1.vendas.domains.dtos;

import java.util.UUID;
import com.av1.vendas.domains.Group;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GroupDTO {

    protected UUID id;

    @NotNull(message = "O campo descrição não pode ser nulo")
    @NotBlank(message = "O campo descrição não pode ser vazio")
    protected String description;

    public GroupDTO() {
    }

    public GroupDTO(Group obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    

}

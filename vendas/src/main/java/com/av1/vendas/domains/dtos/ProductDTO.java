package com.av1.vendas.domains.dtos;

import java.util.UUID;
import com.av1.vendas.domains.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    protected UUID id;

    @NotNull(message = "O campo descrição não pode ser nulo")
    @NotBlank(message = "O campo descrição não pode ser vazio")
    protected String description;
    @NotNull(message = "O campo unidade não pode ser nulo")
    @NotBlank(message = "O campo unidade não pode ser vazio")
    protected String un;
    @NotNull(message = "O campo valor unitario não pode ser nulo")
    @NotBlank(message = "O campo valor unitario não pode ser vazio")
    protected Double unitValue;
    @NotNull(message = "O campo estoque não pode ser nulo")
    @NotBlank(message = "O campo estoque não pode ser vazio")
    protected Double stock;
    @NotNull(message = "O campo produto é requerido")
    private UUID group;
    private String descriptionGroup;
    
    public ProductDTO() {
    }

    public ProductDTO(Product obj) {
        this.id = obj.getId();
        this.description = obj.getDescription();
        this.un = obj.getUn();
        this.unitValue = obj.getUnitValue();
        this.stock = obj.getStock();
        this.group = obj.getGroup().getId();
        this.descriptionGroup = obj.getGroup().getDescription();
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

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public Double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Double unitValue) {
        this.unitValue = unitValue;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public UUID getGroup() {
        return group;
    }

    public void setGroup(UUID group) {
        this.group = group;
    }

    public String getDescriptionGroup() {
        return descriptionGroup;
    }

    public void setDescriptionGroup(String descriptionGroup) {
        this.descriptionGroup = descriptionGroup;
    }

    

}

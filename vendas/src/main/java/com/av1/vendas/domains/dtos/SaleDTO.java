package com.av1.vendas.domains.dtos;

import java.time.LocalDate;
import java.util.UUID;
import com.av1.vendas.domains.Sale;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

public class SaleDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate Date = LocalDate.now();
    @NotNull(message = "O campo total é requerido")
    private Double total;
    @NotNull(message = "O campo observação é requerido")
    private String obs;
    @NotNull(message = "O campo Status da Venda é requerido")
    private Integer saleStatus;
    @NotNull(message = "O campo vendedor é requerido")
    private UUID seller;
    @NotNull(message = "O campo cliente é requerido")
    private UUID client;
    @NotNull(message = "O campo produto é requerido")
    private UUID product;
    private String nameSeller;
    private String nameClient;
    private String descriptionProduct;
    
    public SaleDTO() {
    }

    public SaleDTO(Sale sale) {
        this.id = sale.getId();
        this.Date = sale.getDate();
        this.total = sale.getTotal();
        this.obs = sale.getObs();
        this.saleStatus = sale.getSaleStatus().getId();
        this.seller = sale.getSeller().getId();
        this.client = sale.getClient().getId();
        this.product = sale.getProduct().getId();
        this.nameSeller = sale.getSeller().getFirstName() + " " + sale.getSeller().getLastName();
        this.nameClient = sale.getClient().getFirstName() + " " + sale.getClient().getLastName();
        this.descriptionProduct = sale.getProduct().getDescription();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public UUID getSeller() {
        return seller;
    }

    public void setSeller(UUID seller) {
        this.seller = seller;
    }

    public UUID getClient() {
        return client;
    }

    public void setClient(UUID client) {
        this.client = client;
    }

    public UUID getProduct() {
        return product;
    }

    public void setProduct(UUID product) {
        this.product = product;
    }

    public String getNameSeller() {
        return nameSeller;
    }

    public void setNameSeller(String nameSeller) {
        this.nameSeller = nameSeller;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    
    


}

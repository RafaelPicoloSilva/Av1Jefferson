package com.av1.vendas.domains.enums;

public enum SaleStatus {

    OPEN(0, "ROLE_OPEN"), FINALIZED(1, "ROLE_FINALIZED"), CANCELED(1, "ROLE_CANCELED");

    private Integer id;
    private String saleStatus;
    
    private SaleStatus(Integer id, String saleStatus) {
        this.id = id;
        this.saleStatus = saleStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public static SaleStatus toEnum(Integer id) {
        if(id==null) return null;
        for(SaleStatus x : SaleStatus.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status Inválido");
    }
}

package com.av1.vendas.domains.enums;

public enum PersonStatus {

    ACTIVE(0, "ROLE_ACTIVE"), INATIVE(1, "ROLE_INATIVE");

    private Integer id;
    private String personStatus;
    
    private PersonStatus(Integer id, String personStatus) {
        this.id = id;
        this.personStatus = personStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }

    public static PersonStatus toEnum(Integer id) {
        if(id==null) return null;
        for(PersonStatus x : PersonStatus.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido");
    }
}

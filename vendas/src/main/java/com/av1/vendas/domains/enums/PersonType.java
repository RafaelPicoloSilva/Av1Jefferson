package com.av1.vendas.domains.enums;

public enum PersonType {

    ADMIN(0, "ROLE_ADMINS"), CLIENT(1, "ROLE_CLIENTS"), SELLER(2, "ROLE_SELLERS");

    private Integer id;
    private String personType;
    
    private PersonType(Integer id, String personType) {
        this.id = id;
        this.personType = personType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public static PersonType toEnum(Integer id) {
        if(id==null) return null;
        for(PersonType x : PersonType.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido");
    }
}

package com.apppassarourbano.AppPassaroUrbano.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ROLE_VENDEDOR(01l,"ROLE_VENDEDOR"),
    ROLE_CLIENTE(02l, "ROLE_CLIENTE");

    private Long code;
    private String name;

    public static RoleEnum parse(String src){
        for(RoleEnum role: RoleEnum.values()){
            if(role.getName().equals(src)){
                return role;
            }
        }
        return null;
    }
}

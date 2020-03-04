package br.com.jetapps.app.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EnumSexo {

    MASCULINO,
    FEMININO;
    
    @JsonCreator
    public static EnumSexo getByCodigo(String codigo) {
        return EnumSexo.valueOf(codigo);
    }
}

package br.com.jetapps.app.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EnumTipoCliente {

    CREDIARIO,
    CONVENIO,
    A_VISTA,
    TELE_ENTREGA,
    PREFERENCIAL;
    
    @JsonCreator
    public static EnumTipoCliente getByCodigo(String codigo) {
        return EnumTipoCliente.valueOf(codigo);
    }
}

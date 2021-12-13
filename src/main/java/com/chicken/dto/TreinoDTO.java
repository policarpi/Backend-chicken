package com.chicken.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreinoDTO {

    private Integer idPessoa;
    private Integer idEquipamentos;
    private Integer quantidade;
    private Integer repeticao;
    private Double peso;
    private String ficha;

    public TreinoDTO() {

    }
}

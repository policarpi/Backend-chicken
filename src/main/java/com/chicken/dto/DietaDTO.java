package com.chicken.dto;

import lombok.Getter;
import lombok.Setter;


@Getter@Setter
public class DietaDTO {

    private Integer idPessoa;
    private Integer idAlimentos;
    private Integer idRefeicoes;
    private Double quantidade;

    public DietaDTO(){

    }
}

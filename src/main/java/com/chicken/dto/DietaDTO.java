package com.chicken.dto;

import com.chicken.entity.Alimentos;
import com.chicken.entity.Pessoa;
import com.chicken.entity.Refeicoes;
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

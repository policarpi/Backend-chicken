package com.chicken.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaEnderecoDTO {

    Integer idPessoa;
    String cep;
    String endereco;
    Integer numero;
    String cidade;
    String estado;


    public PessoaEnderecoDTO(){

    }
}

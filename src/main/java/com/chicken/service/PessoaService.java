package com.chicken.service;

import com.chicken.entity.Dieta;
import com.chicken.entity.Pessoa;
import com.chicken.repository.PessoaRepository;

public class PessoaService {



    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    public Pessoa getPessoa(String nome){
        return  pessoaRepository.findPessa(nome);
    }




}

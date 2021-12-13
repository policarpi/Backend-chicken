package com.chicken.service;

import com.chicken.repository.PessoaRepository;

public class PessoaService {



    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


}

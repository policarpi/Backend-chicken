package com.chicken.service;

import com.chicken.entity.Dieta;
import com.chicken.repository.DietaRepository;

import java.util.List;

public class DietaService {

    private  final DietaRepository dietaRepository;

    public DietaService(DietaRepository dietaRepository) {
        this.dietaRepository = dietaRepository;
    }

    public Dieta getDieta(Long id){
        return  dietaRepository.findDieta(id);
    }

    public List<Dieta> getDieta(String nome){
        return dietaRepository.findByNomePessoa(nome);
    }


}

package com.chicken.rest;


import com.chicken.entity.Carrinho;
import com.chicken.repository.CarrinhoRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private  CarrinhoRepository carrinhoRepository;

    public List<Carrinho> listar(){
        return carrinhoRepository.findAll();
    }



}


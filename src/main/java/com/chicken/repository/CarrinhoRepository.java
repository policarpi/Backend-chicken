package com.chicken.repository;

import com.chicken.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
}

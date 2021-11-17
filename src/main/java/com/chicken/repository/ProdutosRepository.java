package com.chicken.repository;

import com.chicken.entity.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository <Produtos, Integer> {
}

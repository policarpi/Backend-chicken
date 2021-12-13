package com.chicken.repository;

import com.chicken.entity.Refeicoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefeicoesRepository extends JpaRepository <Refeicoes, Integer> {
}

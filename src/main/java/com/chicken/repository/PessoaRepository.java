package com.chicken.repository;

import com.chicken.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}

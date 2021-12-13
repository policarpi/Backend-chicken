package com.chicken.repository;

import com.chicken.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {


}

package com.chicken.repository;

import com.chicken.entity.Dieta;
import com.chicken.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


    @Query("select a from Pessoa a where a.id = ?1")
    public Pessoa findPessa (String id);

}

package com.chicken.repository;

import com.chicken.entity.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnderecosRepository extends JpaRepository<Enderecos, Long> {

    @Query(" select n from Enderecos n join n.pessoa a where upper(a.nome) like upper(:nome) ")
    List<Enderecos> findByNome(@Param("nome") String nome);
}

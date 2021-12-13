package com.chicken.repository;

import com.chicken.entity.Fisiologia;
import com.chicken.entity.Treinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FisiologiaRepository extends JpaRepository<Fisiologia, Integer> {

    @Query(" select n from Fisiologia n join n.pessoa a where upper(a.nome) like upper(:nome) ")
    List<Fisiologia> findByNome(@Param("nome") String nome);

}
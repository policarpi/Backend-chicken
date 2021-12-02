package com.chicken.repository;

import com.chicken.entity.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DietaRepository extends JpaRepository<Dieta, Long> {


    @Query("select a from Dieta a where a.id = ?1")
    public Dieta findDieta (Long id);


    @Query(" select n from Dieta n join n.pessoa a where upper(a.nome) like upper(:nome) ")
    List<Dieta> findByNomePessoa(@Param("nome") String nome);

}
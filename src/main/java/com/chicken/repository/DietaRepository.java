package com.chicken.repository;

import com.chicken.entity.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long> {

    @Query(" select n from Dieta n join n.pessoa a where upper(a.nome) like upper(:nome) ")
    List<Dieta> findByNomePessoa(@Param("nome") String nome);

}
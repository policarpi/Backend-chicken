package com.chicken.repository;

import com.chicken.entity.Alimentos;
import com.chicken.entity.AlimentosRestricao;
import com.chicken.entity.Treinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentosRestricaoRepository extends JpaRepository <AlimentosRestricao, Long> {

    @Query(" select n from AlimentosRestricao  n join n.alimentos a where upper(a.nome) like upper(:nome) ")
    List<AlimentosRestricao> findByNome(@Param("nome") String nome);


}

package com.chicken.repository;

import com.chicken.entity.Treinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TreinosRepository extends JpaRepository<Treinos, Long> {

    @Query(" select n from Treinos n join n.pessoa a where upper(a.nome) like upper(:nome) ")
    List<Treinos> findByNome(@Param("nome") String nome);



 /*   @Query(" select n from Treinos n join n.pessoa a where upper(a.nome) like upper(:nome) and" +
            "select n from Treinos n join n.equipamentos a where upper(a.nome) like upper(:nome)  ")
    List<Treinos> findByNome(@Param("nome") String nome);
*/


}

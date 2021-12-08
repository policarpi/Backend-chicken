package com.chicken.repository;

import com.chicken.entity.AlimentosRestricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentosRestricaoRepository extends JpaRepository <AlimentosRestricao, Long> {
}

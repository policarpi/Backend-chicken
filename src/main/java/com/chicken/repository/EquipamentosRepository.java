package com.chicken.repository;

import com.chicken.entity.Equipametos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipamentosRepository extends JpaRepository <Equipametos, Long> {
}

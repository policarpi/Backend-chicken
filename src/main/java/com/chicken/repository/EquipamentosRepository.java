package com.chicken.repository;

import com.chicken.entity.Equipametos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentosRepository extends JpaRepository <Equipametos, Integer> {
}

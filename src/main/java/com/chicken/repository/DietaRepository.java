package com.chicken.repository;

import com.chicken.entity.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietaRepository extends JpaRepository<Dieta, Integer> {
}

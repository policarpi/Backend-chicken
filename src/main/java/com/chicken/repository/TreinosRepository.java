package com.chicken.repository;

import com.chicken.entity.Treinos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinosRepository extends JpaRepository <Treinos, Integer> {
}

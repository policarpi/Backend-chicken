package com.chicken.repository;

import com.chicken.entity.Treinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TreinosRepository extends JpaRepository <Treinos, Long> {
}

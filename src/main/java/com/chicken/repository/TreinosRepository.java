package com.chicken.repository;

import com.chicken.entity.Pessoa;
import com.chicken.entity.Treinos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreinosRepository extends JpaRepository <Treinos, Long> {
}

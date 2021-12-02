package com.chicken.repository;

import com.chicken.entity.Fisiologia;
import com.chicken.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FisiologiaRepository extends JpaRepository<Fisiologia, Long> {



}
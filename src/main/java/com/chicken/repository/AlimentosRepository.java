package com.chicken.repository;

import com.chicken.entity.Alimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentosRepository extends JpaRepository<Alimentos,Long> {
}

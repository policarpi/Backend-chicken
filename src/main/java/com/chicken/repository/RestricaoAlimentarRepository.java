package com.chicken.repository;


import com.chicken.entity.RestricaoAlimentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestricaoAlimentarRepository extends JpaRepository <RestricaoAlimentar, Integer> {
}

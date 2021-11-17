package com.chicken.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;





}

package com.chicken.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class MeusPedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



}

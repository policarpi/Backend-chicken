package com.chicken.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;

@Entity
@Getter@Setter
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column
    @NotNull
    private  String marca;

    @Column
    @NotNull
    private String sabor;

    @Column
    @NotNull
    private String igredientes;

    @Column
    @NotNull
    private String infoAlergicos;

    @Column
    @NotNull
    private Double Peso;

    @Column
    @NotNull
    private String itemDimencao;

}

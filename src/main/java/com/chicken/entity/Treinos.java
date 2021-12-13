package com.chicken.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter@Setter
public class Treinos  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_equipamento")
    private Equipamentos equipamentos;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column
    private Integer quantidade;

    @Column
    private Integer repeticao;

    @Column
    private Double peso;

    @Column
    private String ficha;

}

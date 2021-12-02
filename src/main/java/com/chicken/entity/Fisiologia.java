package com.chicken.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "Fisiologia", indexes = {
        @Index(name = "idx_fisiologia_altura", columnList = "altura")
})
@Entity
@Getter
@Setter
public class Fisiologia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column
    @NotNull
    private String tipo;

    @Column
    @NotNull
    private String nome;

    @Column
    @NotNull
    private Double peso;


    @Column(name = "altura")
    @NotNull
    private Double altura;



}

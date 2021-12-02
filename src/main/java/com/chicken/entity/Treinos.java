package com.chicken.entity;



import com.sun.istack.NotNull;
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
    private Equipametos equipametos;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column
    @NotNull
    private Integer quantidade;

    @Column
    @NotNull
    private Integer repeticao;

    @Column
    @NotNull
    private Double peso;

    @Column
    @NotNull
    private String ficha;

}

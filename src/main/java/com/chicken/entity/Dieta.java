package com.chicken.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;


    @ManyToOne
    @JoinColumn(name = "id_alimentos")
    private Alimentos alimentos;


    @ManyToOne
    @JoinColumn(name = "id_refeicoes")
    private Refeicoes refeicoes;

    @Column
    @NotNull
    private Double quantidade;


}

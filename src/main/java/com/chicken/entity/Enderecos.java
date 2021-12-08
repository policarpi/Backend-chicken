package com.chicken.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Enderecos   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column
    @NotNull
    private String cep;

    @Column
    @NotNull
    private String endereco;

    @Column
    @NotNull
    private Integer numero;

    @Column
    @NotNull
    private String cidade;

    @Column
    @NotNull
    private String estado;
}

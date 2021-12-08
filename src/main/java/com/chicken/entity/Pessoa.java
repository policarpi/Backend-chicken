package com.chicken.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String nome;

    @Column
    @NotNull
    private String sobreNome;

    @Column
    @NotNull
    private String dataNacimento;

    @Column
    @NotNull
    private String eMail;

    @Column
    @NotNull
    private String cpf;
}

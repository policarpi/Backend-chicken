package com.chicken.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.jnlp.DownloadService;
import javax.persistence.*;


@Entity
@Getter
@Setter
public class Planos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private Integer plano;

    @Column
    @NotNull
    private Double valorPlano;

}

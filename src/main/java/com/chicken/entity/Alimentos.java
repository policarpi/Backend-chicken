package com.chicken.entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter@Setter
public class Alimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @NotEmpty
    @Column(name = "descricao",length = 100)
    private String descricao;

    @Min(value = 5, message = "Quantidade minima para calculo é 5g")
    @NotEmpty
    @Column(name = "porcao")
    private Double porcao;

    @Min(value = 1, message = "Alimento não pode ter menos de 1kcal")
    @NotEmpty
    @Column(name = "kcal")
    private Double kcal;

    @Min(value = 1, message = "Alimento não pode ter menos de 1kcal")
    @NotEmpty
    @Column(name = "carboidratos")
    private Double carboidratos;

    @NotEmpty
    @Column(name = "gordurasTotais")
    private Double gordurasTotais;

    @NotEmpty
    @Column(name = "gorduraSaturadas")
    private Double gorduraSaturadas;

    @NotEmpty
    @Column(name = "gorduraTrans")
    private Double gorduraTrans;

    @NotEmpty
    @Column(name = "proteinas")
    private Double proteinas;


    @NotEmpty
    @Column(name = "fibraAlimentar")
    private Double fibraAlimentar;

}

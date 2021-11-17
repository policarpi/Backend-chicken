package com.chicken.entity;



import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter@Setter
public class Treinos  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column
    @NotNull
    private Integer quantidade;

    @Column
    @NotNull
    private Integer repeticao;

    @Column
    @NotNull
    private Double peso;

}

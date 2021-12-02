package com.chicken.entity;

import lombok.Getter;
import lombok.Setter;



import javax.persistence.*;


@Entity
@Getter
@Setter
public class AlimentosRestricao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_alimentos")
    private Alimentos alimentos;


    @ManyToOne
    @JoinColumn(name = "id_restricaoAlimentar")
    private RestricaoAlimentar restricaoAlimentar;


}

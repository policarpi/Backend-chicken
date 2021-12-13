package com.chicken.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter@Setter
public class Alimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nome;

    @NotEmpty
    @Column(name = "descricao",length = 100)
    private String descricao;

    @Column
    private Double carboidrato;

    @Column
    private Double colesterol;

    @Column
    private Double fibra;

    @Column
    private Double luteinazeaxantina;

    @Column
    private Double licopeno;

    @Column
    private Double niacina;

    @Column
    private Double proteina;

    @Column
    private Double retinol;

    @Column
    private Double riboflavina;

    @Column
    private Double selenio;

    @Column
    private Double acucartotal;

    @Column
    private Double tiamina;

    @Column
    private Double agua;

    @Column
    private Double gorduramonosaturada;

    @Column
    private Double gordurapolissaturada;

    @Column
    private Double gordurasaturada;

    @Column
    private Double lipidio;

    @Column
    private Double mineralcalcio;

    @Column
    private Double mineralscopper;

    @Column
    private Double mineralferro;

    @Column
    private Double mineralsmagnesio;

    @Column
    private Double mineralfosforo;

    @Column
    private Double mineralpotassio;

    @Column
    private Double mineralsodio;

    @Column
    private Double mineralzinco;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Alimentos alimentos = (Alimentos) o;
        return id != null && Objects.equals(id, alimentos.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

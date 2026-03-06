package com.edulanzarin.erp.comum.usuario.model;

import com.edulanzarin.erp.core.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permissao extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String nome;

    @Column
    private String descricao;
}

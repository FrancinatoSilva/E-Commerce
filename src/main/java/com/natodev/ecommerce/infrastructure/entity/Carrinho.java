package com.natodev.ecommerce.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "usuario_id")
    private Integer usuarioId; // Conferir lógica de forest key!!!

    @Column(name = "data_criacao")
    private LocalDate dataCriacao = LocalDate.now();

    @Column(name = "status")
    private String status;


}

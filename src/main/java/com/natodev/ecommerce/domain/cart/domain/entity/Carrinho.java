package com.natodev.ecommerce.domain.cart.domain.entity;

import com.natodev.ecommerce.domain.user.domain.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carrinho")
public class Carrinho {

    @Id
    @Column(name = "carrinho_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID carrinhoId;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao = LocalDate.now();

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "carrinho")
    private List<ItemCarrinho> itens;


}

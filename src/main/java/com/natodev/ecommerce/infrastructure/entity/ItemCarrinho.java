package com.natodev.ecommerce.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_carrinho")
public class ItemCarrinho {

    @Id
    @Column(name = "item_carrinho_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID itemCarrinhoId;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    @Column(name = "produto_id")
    private Produto produto; // Verificar lógica de foreign key

    @Column (name = "quantidade")
    private Integer quantidade;

    @Column (name = "preco_unitario")
    private BigDecimal precoUnitario;
}

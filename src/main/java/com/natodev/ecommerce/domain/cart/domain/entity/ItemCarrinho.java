package com.natodev.ecommerce.domain.cart.domain.entity;

import com.natodev.ecommerce.domain.product.domain.entity.Produto;
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

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column (name = "quantidade")
    private Integer quantidade;

    @Column (name = "preco_unitario")
    private BigDecimal precoUnitario;
}
